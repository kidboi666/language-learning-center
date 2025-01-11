import jwt from "jsonwebtoken";
import { RequestHandler } from "express";

export const isLoggedIn: RequestHandler = (req, res, next) => {
  if (req.isAuthenticated()) {
    next();
  } else {
    res.status(403).send("Not Authenticated");
  }
};

export const isNotLoggedIn: RequestHandler = (req, res, next) => {
  if (!req.isAuthenticated()) {
    next();
  } else {
    res.status(403).send("Already Authenticated");
  }
};

export const verifyToken: RequestHandler = (req, res, next) => {
  try {
    res.locals.decoded = jwt.verify(
      req.headers.authorization!,
      process.env.JWT_SECRET!,
    );
    next();
  } catch (err) {
    if (err instanceof Error) {
      if (err.name === "TokenExpired") {
        res.status(419).json({
          message: "Token expired",
        });
      }
    }
    res.status(500).json({ message: "알 수 없는 에러가 발생했습니다." });
  }
};
