import app from "../app";
import request from "supertest";
import db from "../db";
import { beforeEach, describe, test } from "@jest/globals";

describe("/auth", () => {
  const newUser = {
    email: "test@test.com",
    password: "123456",
  };

  beforeEach(async () => {
    await db.none(`TRUNCATE TABLE users RESTART IDENTITY CASCADE`);
  });

  describe("POST /auth/signup", () => {
    test("회원가입", async () => {
      await request(app).post("/auth/signup").send(newUser).expect(201);
    });

    test("중복 회원가입", async () => {
      await request(app).post("/auth/signup").send(newUser);

      await request(app).post("/auth/signup").send(newUser).expect(403);
    });
  });

  describe("POST /auth/login", () => {
    test("로그인", async () => {
      await request(app).post("/auth/signup").send(newUser);
      await request(app).post("/auth/login").send(newUser).expect(200);
    });

    let loginAgent = request.agent(app);

    test("중복 로그인", async () => {
      await loginAgent.post("/auth/signup").send(newUser);
      await loginAgent.post("/auth/login").send(newUser);
      await loginAgent.post("/auth/login").send(newUser).expect(403);
    });
  });

  describe("POST /auth/logout", () => {
    let logoutAgent = request.agent(app);

    test("로그아웃", async () => {
      await logoutAgent.post("/auth/signup").send(newUser);
      await logoutAgent.post("/auth/login").send(newUser);
      await logoutAgent.post("/auth/logout").send().expect(204);
    });

    test("비로그인시 로그아웃", async () => {
      await request(app).post("/auth/logout").send().expect(403);
    });
  });
});
