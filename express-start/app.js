const express = require("express");
const path = require("path");
const morgan = require("morgan");
const cookieParser = require("cookie-parser");

const app = express();

app.set("port", process.env.PORT || 3000);

app.use(morgan("dev"));
app.use(cookieParser());

/**
 * 공통 미들웨어
 */
app.use((req, res, next) => {
  console.log("요청에 실행하고 싶어요");
  next();
});

app.get(
  "/",
  (req, res, next) => {
    res.sendFile(path.join(__dirname, "index.html"));
    if (true) {
      next("route");
    } else {
      next();
    }
  },
  (req, res) => {
    console.log("실행되나요?");
  }
);

app.post("/", (req, res) => {
  res.send("hello express");
});

app.get("/category/:name", (req, res) => {
  res.send(`hello ${req.params.name}`);
});

app.use((req, res, next) => {
  res.send();
  next();
});

app.use((err, req, res, next) => {
  console.error("에러남");
  res.status(500).send("dasdsdfff");
});

app.listen(app.get("port"), () => {
  console.log("Express server started on port 3000");
});
