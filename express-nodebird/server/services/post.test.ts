import app from "../app";
import request from "supertest";
import db from "../db";
import { afterAll, afterEach, beforeAll, describe, test } from "@jest/globals";

describe("/post", () => {
  const testUser = {
    email: "test@example.com",
    password: "123456",
  };

  beforeAll(async () => {
    await request(app).post("/auth/signup").send(testUser);
  });

  afterEach(async () => {
    await db.none(`TRUNCATE TABLE posts RESTART IDENTITY CASCADE`);
  });

  afterAll(async () => {
    await db.none(`TRUNCATE TABLE users RESTART IDENTITY CASCADE`);
  });

  const newPost = {
    title: "New Post",
    content: "New Post Lorem Ipsum",
    image: "",
    userId: 1,
  };

  describe("POST /post", () => {
    test("비로그인시 포스팅", async () => {
      await request(app).post("/post").send(newPost).expect(403);
    });

    let postWithLoginAgent = request.agent(app);
    test("로그인시 포스팅", async () => {
      await postWithLoginAgent.post("/auth/login").send(testUser);
      await postWithLoginAgent.post("/post").send(newPost).expect(201);
    });

    test("데이터 검증", async () => {
      await postWithLoginAgent
        .post("/post")
        .send({ title: "wrong post", userId: 1 })
        .expect(400, "본문이 비었습니다.");
      await postWithLoginAgent
        .post("/post")
        .send({ content: "wrong post", userId: 1 })
        .expect(400, "제목이 비었습니다.");
      await postWithLoginAgent
        .post("/post")
        .send({ content: "wrong post", title: "wrong post" })
        .expect(400, "유저아이디가 비었습니다.");
      await postWithLoginAgent
        .post("/post")
        .send({ content: "wrong post", title: "wrong post", userId: 1 })
        .expect(201);
    });
  });
});
