const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const db = require('../db');
const bcrypt = require('bcrypt');

module.exports = () => {
  passport.use(
    new LocalStrategy(
      {
        usernameField: 'email',
        passwordField: 'password',
      },
      async (email, password, done) => {
        const query = 'select * from users where email = $1';

        try {
          const user = await db.oneOrNone(query, [email]);

          if (user) {
            const result = await bcrypt.compare(password, user.password);
            if (result) {
              done(null, user);
            } else {
              done(null, false, { message: 'Passwords do not match' });
            }
          } else {
            return done(null, false, { message: 'Email does not exist' });
          }
        } catch (err) {
          console.error(err);
          done(err);
        }
      },
    ),
  );
};
