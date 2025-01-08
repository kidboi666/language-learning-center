const passport = require('passport');
const local = require('./local-strategy');
const kakao = require('./kakao-strategy');
const passportService = require('../services/passport-service');

module.exports = () => {
  passport.serializeUser((user, done) => {
    done(null, user.id);
  });

  passport.deserializeUser(async (id, done) => {
    try {
      const user = await passportService.getUserById(id);
      done(null, user);
    } catch (err) {
      done(err);
    }
  });

  local();
  kakao();
};
