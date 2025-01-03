const passport = require('passport');
const local = require('./local-strategy');
const kakao = require('./kakao-strategy');
const db = require('../db');

module.exports = () => {
  passport.serializeUser((user, done) => {
    done(null, user.id);
  });

  passport.deserializeUser((id, done) => {
    const query = 'select * from users where id = $1';

    db.oneOrNone(query, [id])
      .then((user) => {
        done(null, user);
      })
      .catch((err) => {
        done(err);
      });
  });

  local();
  kakao();
};
