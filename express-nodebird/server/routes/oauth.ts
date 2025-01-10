import express from "express";
import passport from "passport";
import { isNotLoggedIn } from "../middlewares";

const router = express.Router();

router.get("/kakao", isNotLoggedIn, passport.authenticate("kakao"));

router.get(
  "/kakao/callback",
  passport.authenticate("kakao", {
    failureRedirect: "/?loginError=kakaoLoginFailed",
  }),
  (req, res) => {
    res.redirect(process.env.CLIENT_URL || "/");
  },
);

export default router;
