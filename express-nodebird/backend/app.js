const express = require('express');
const cookieParser = require('cookie-parser');
const morgan = require('morgan');
const path = require('path');
const dotenv = require('dotenv');
const passportConfig = require('./passport');
const passport = require('passport');
const session = require('express-session');
// const RedisStore = require('connect-redis')(session);
// const redis = require('redis');
const userRouter = require('./routes/user');
const authRouter = require('./routes/auth');

dotenv.config();

const app = express();
passportConfig();
app.set('port', process.env.PORT || 8001);

// const redisClient = redis.createClient({ legacyMode: true });

app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser(process.env.COOKIE_SECRET));
app.use(
  session({
    resave: false,
    saveUninitialized: true,
    secret: process.env.COOKIE_SECRET,
    // store: new RedisStore({ client: redisClient }),
    cookie: {
      secure: false,
      maxAge: 60 * 60 * 1000,
    },
  }),
);
app.use(passport.initialize()); // req.user, req.login, req.isAuthenticate, req.logout
app.use(passport.session());

app.get('/', (req, res, next) => {
  if (req.session.num === undefined) {
    req.session.num = 1;
  } else {
    req.session.num = req.session.num + 1;
  }
  console.log(req.session);
  res.json({ message: `view count: ${req.session.num}` });
});
app.use('/user', userRouter);
app.use('/auth', authRouter);

app.use((req, res, next) => {
  const error = new Error('Not Found');
  error.status = 404;
  next(error);
});

app.use((err, req, res, next) => {
  console.error(err);
  res.status(err?.status || 500).json({
    error: {
      message: err.message,
    },
  });
});

app.listen(app.get('port'), async () => {
  console.log(`Listening on port ${app.get('port')}`);
});
