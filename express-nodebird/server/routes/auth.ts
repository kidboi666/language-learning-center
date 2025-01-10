import express from "express";

const router = express.Router();

import {
  updateUser,
  createUser,
  login,
  checkSession,
  logout,
} from "../controllers/auth.controller";
import { isLoggedIn, isNotLoggedIn } from "../middlewares";

router.patch("/:id", isLoggedIn, updateUser);
router.post("/signup", isNotLoggedIn, createUser);
router.post("/login", isNotLoggedIn, login);
router.post("/logout", isLoggedIn, logout);
router.get("/session", checkSession);

export default router;
