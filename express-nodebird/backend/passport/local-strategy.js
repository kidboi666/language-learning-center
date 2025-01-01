const passport = require('passport');
const LocalStrategy = require('passport-local');
const db = require('../db');
const bcrypt = require('bcrypt');

module.exports = () => {
  passport.use(
    new LocalStrategy(
      {
        usernameField: 'email',
        passwordField: 'password',
        _passReqToCallback: false,
      },
      async (email, password, done) => {
        const query = 'select * from users where email = $1';

        try {
          const user = await db.oneOrNone(query, [email]);
          if (!user) {
            return done(null, false, { message: 'Email does not exist' });
          }

          const isMatch = await bcrypt.compare(password, user.password);
          if (!isMatch) {
            return done(null, false, { message: 'Passwords do not match' });
          }

          return done(null, user);
        } catch (err) {
          console.error(err);
          done(err);
        }
      },
    ),
  );
};
