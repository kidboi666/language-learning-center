const authService = require('../services/auth-service');

exports.login = async (req, res, next) => {
  try {
    await authService.loginUser(req, res, next);
  } catch (err) {
    console.error(err);
  }
};

exports.logout = async (req, res, next) => {
  req.logout(() => {
    res.redirect('/');
  });
};

exports.createUser = async (req, res) => {
  const { email, password } = req.body;

  try {
    await authService.createUser({
      email,
      password,
    });
    res.status(200).send();
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: err.message });
  }
};

exports.updateUser = async (req, res) => {
  const { id } = req.params;
  const { name, avatarUrl } = req.body;

  try {
    await authService.updateUser({
      id,
      name,
      avatarUrl,
    });
    res.status(200).send();
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: err.message });
  }
};
