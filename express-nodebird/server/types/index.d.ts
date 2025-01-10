import { NextFunction, Request, Response } from "express";

declare global {
  enum Provider {
    LOCAL = "local",
    KAKAO = "kakao",
  }

  namespace Express {
    interface User {
      id: number;
      provider: Provider;
    }
  }
  interface Error {
    status: number;
  }
}

export {};
