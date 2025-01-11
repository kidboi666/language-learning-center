import { describe, expect, jest, test } from "@jest/globals";
import { isLoggedIn, isNotLoggedIn, verifyToken } from "./index";
import { Request, Response } from "express";

describe("isLoggedIn", () => {
  const res = {
    status: jest.fn(() => res),
    send: jest.fn(),
    locals: {
      decoded: "token1",
    },
    json: jest.fn(() => {}),
  } as unknown as Response;
  const next = jest.fn();

  test("로그인되어 있으면 isLoggedIn이 next를 호출해야 함", () => {
    const req = {
      isAuthenticated: jest.fn(() => true),
    } as unknown as Request;
    isLoggedIn(req, res, next);
    expect(next).toBeCalledTimes(1);
  });

  test("로그인 되어 있지 않으면 isLoggedIn이 에러를 응답해야 함", () => {
    const req = {
      isAuthenticated: jest.fn(() => false),
    } as unknown as Request;
    isLoggedIn(req, res, next);
    expect(res.status).toBeCalledWith(403);
    expect(res.send).toBeCalledWith("Not Authenticated");
  });

  test("로그인 되어 있고 토큰이 일치하지 않을때 에러를 응답해야 함", () => {
    const req = {
      headers: {
        authorization: "token2",
      },
    } as unknown as Request;
    verifyToken(req, res, next);
    expect(res.status).toBeCalledWith(401);
    expect(res.json).toBeCalledWith({ message: "Unauthorized" });
  });
});

describe("isNotLoggedIn", () => {
  const res = {
    status: jest.fn(() => res),
    send: jest.fn(),
    locals: {
      decoded: "token1",
    },
    json: jest.fn(() => {}),
  } as unknown as Response;
  const next = jest.fn();

  test("로그인되어 있으면 isNotLoggedIn이 에러를 응답해야 함", () => {
    const req = {
      isAuthenticated: jest.fn(() => true),
    } as unknown as Request;
    isNotLoggedIn(req, res, next);
    expect(res.status).toBeCalledWith(403);
    expect(res.send).toBeCalledWith("Already Authenticated");
  });

  test("로그인되어 있지 않으면 isNotLoggedIn이 next를 호출해야 함", () => {
    const req = {
      isAuthenticated: jest.fn(() => false),
    } as unknown as Request;
    isNotLoggedIn(req, res, next);
    expect(next).toBeCalledTimes(1);
  });
});
