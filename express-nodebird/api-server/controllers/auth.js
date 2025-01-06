const bcrypt = require('bcrypt');
const passport = require('passport');
const db = require('../db');

exports.join = async (req, res, next) => {
  const { email, name, password } = req.body;
  const userFetchQuery = 'SELECT * FROM users WHERE email = ?';
  const userInsertQuery =
    'INSERT INTO users(email, name, password) VALUES (?,?,?)';

  try {
    const user = await db.oneOrNone(userFetchQuery, [email]);

    if (user) {
      return res.redirect('/join?error=exist');
    }

    const hash = await bcrypt.hash(password, 12);
    await db.none(userInsertQuery, [email, name, hash]);
    return res.redirect('/');
  } catch (err) {
    console.error(err);
    next(err);
  }
};

exports.login = async (req, res, next) => {
  passport.authenticate('local', (authError, user, info) => {
    if (authError) {
      console.error(authError);
      return next(authError);
    }

    if (!user) {
      return res.redirect(`/?error=${info.message}`);
    }

    return req.login(user, (loginError) => {
      if (loginError) {
        console.error(loginError);
        return next(loginError);
      }
      return res.redirect('/');
    });
  })(req, res, next);
};

exports.logout = (req, res) => {
  req.logout(() => {
    res.redirect('/');
  });
};
