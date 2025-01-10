import passport from "passport";
import express, { ErrorRequestHandler } from "express";
import cookieParser from "cookie-parser";
import morgan from "morgan";
import path from "path";
import dotenv from "dotenv";
import passportConfig from "./passport/index";
import session from "express-session";
import cors from "cors";
import authRouter from "./routes/auth";
import oauthRouter from "./routes/oauth";
import postRouter from "./routes/post";

dotenv.config();

const app = express();
passportConfig();
app.set("port", process.env.PORT || 8001);

app.use(
  cors({
    origin: process.env.CLIENT_URL,
    credentials: true,
  }),
);

app.use(morgan("dev"));
app.use(express.static(path.join(__dirname, "public")));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser(process.env.COOKIE_SECRET));
app.use(
  session({
    resave: false,
    saveUninitialized: false,
    secret: process.env.COOKIE_SECRET!,
    cookie: {
      httpOnly: true,
      secure: false,
    },
    name: "session-cookie",
  }),
);
app.use(passport.initialize()); // req.user, req.login, req.isAuthenticate, req.logout
app.use(passport.session());

app.use("/auth", authRouter);
app.use("/post", postRouter);
app.use("/oauth", oauthRouter);

app.use((req, res, next) => {
  const error = new Error("Not Found");
  error.status = 404;
  next(error);
});

const errorHandler: ErrorRequestHandler = (err, req, res, next) => {
  console.error(err);
  res.status(err?.status || 500).json({
    error: {
      message: err.message,
    },
  });
};

app.use(errorHandler);

export default app;
