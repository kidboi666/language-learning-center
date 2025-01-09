const authService = require('../services/auth.service');
const passport = require('passport');

exports.login = (req, res, next) => {
  passport.authenticate('local', (authError, user, info) => {
    if (authError) {
      return next(authError);
    }

    if (!user) {
      return res.status(403).send(info.message);
    }

    return req.login(user, (loginError) => {
      if (loginError) {
        return next(loginError);
      }

      return res.json(user);
    });
  })(req, res, next);
};

exports.logout = async (req, res, next) => {
  if (req.isAuthenticated()) {
    req.logout(() => {
      res.status(204).send();
    });
  } else {
    res.status(403).send('You are not logged in');
  }
};

exports.createUser = async (req, res, next) => {
  const { email, password } = req.body;

  try {
    const isValid = await authService.checkEmailExists(email);
    if (isValid) {
      const error = new Error('Email already exists');
      error.status = 403;
      throw error;
    }
    await authService.createUser(email, password);
    res.status(201).send();
  } catch (err) {
    return res.status(err.status).send(err.message);
  }
};

exports.updateUser = async (req, res, next) => {
  const { id } = req.params;
  const { name, avatarUrl } = req.body;

  try {
    await authService.updateUser({
      id,
      nick: name,
      avatarUrl,
    });
    res.send();
  } catch (err) {
    next(err);
  }
};

exports.checkSession = async (req, res, next) => {
  if (req.isAuthenticated()) {
    res.json({ isLoggedIn: true, user: req.user });
  } else {
    res.json({ isLoggedIn: false });
  }
};
