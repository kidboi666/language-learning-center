import express from "express";
import { isLoggedIn } from "../middlewares";
import { uploadImage, uploadPost } from "../controllers/post.controller";

const router = express.Router();

router.post("/image", isLoggedIn, uploadImage);
router.post("/", isLoggedIn, uploadPost);

export default router;
