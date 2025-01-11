import postService from "../services/post.service";
import { RequestHandler } from "express";

export const uploadImage: RequestHandler = (req, res, next) => {
  try {
    postService.uploadImage();
    res.json({ url: `/image/${req.file?.filename}` });
  } catch (err) {
    console.error(err);
    next(err);
  }
};

export const uploadPost: RequestHandler = async (req, res, next) => {
  const { title, content, image, userId } = req.body;

  if (!title) {
    res.status(400).send("제목이 비었습니다.");
  }
  if (!content) {
    res.status(400).send("본문이 비었습니다.");
  }
  if (!userId) {
    res.status(400).send("유저아이디가 비었습니다.");
  }

  try {
    await postService.uploadPost(title, content, image, userId);
    res.status(201).send();
  } catch (err) {
    console.error(err);
    next(err);
  }
};
