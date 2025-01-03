const express = require('express');
const router = express.Router();
const passport = require('passport');

router.get('/kakao', passport.authenticate('kakao'));

router.get(
  '/kakao/callback',
  passport.authenticate('kakao', {
    failureRedirect: '/?loginError=kakaoLoginFailed',
  }),
  (req, res) => {
    res.redirect(process.env.CLIENT_URL || '/');
  },
);

module.exports = router;
