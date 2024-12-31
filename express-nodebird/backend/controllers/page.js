const userService = require('../services/userService');

exports.getUserById = async (req, res) => {
  const {id} = req.params;
  try {
    const user = await userService.getUserById(id);
    res.json(user);
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