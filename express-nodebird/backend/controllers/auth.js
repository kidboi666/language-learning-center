const authService = require('../services/auth-service');
const passport = require('passport');

exports.login = (req, res, next) => {
  passport.authenticate('local', (authError, user, info) => {
    if (authError) {
      return next(authError);
    }

    if (!user) {
      return res.json(info);
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
  req.logout(() => {
    res.send();
  });
};

exports.createUser = async (req, res, next) => {
  const { email, password } = req.body;

  try {
    await authService.createUser(req, res, next, {
      email,
      password,
    });
    res.send();
  } catch (err) {
    next(err);
  }
};

exports.updateUser = async (req, res, next) => {
  const { id } = req.params;
  const { name, avatarUrl } = req.body;

  try {
    await authService.updateUser({
      id,
      name,
      avatarUrl,
    });
    res.send();
  } catch (err) {
    next(err);
  }
};

exports.checkSession = async (req, res, next) => {
  console.log(req.session);
  if (req.isAuthenticated()) {
    res.json({ isLoggedIn: true });
  } else {
    res.json({ isLoggedIn: false });
  }
};

exports.getUserInfo = async (req, res, next) => {
  if (req.isAuthenticated()) {
    res.json(req);
  } else {
    res.json({ message: 'You are not logged in' });
  }
};
