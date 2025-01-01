const passport = require('passport');
const local = require('./local-strategy');
const kakao = require('./kakao-strategy');
const db = require('../db');

module.exports = () => {
  passport.serializeUser(async (user, done) => {
    done(null, user.id);
  });

  passport.deserializeUser(async (id, done) => {
    const query = 'select * from users where id = $1';

    try {
      const user = await db.one(query, [id]);
      done(null, user);
    } catch (err) {
      done(err);
    }
  });

  local();
};
