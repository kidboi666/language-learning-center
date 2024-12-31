const userService = require('../services/userService');

exports.getUserByEmail = async (req, res) => {
  const {email} = req.params;
  try {
    const users = await userService.getUserByEmail(email);
    res.json(users);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.createUser = async (req, res) => {
  const { email, password } = req.body;
  try {
    await userService.createUser({
      email,
      password,
    });
    res.status(200).send();
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: err.message });
  }
}