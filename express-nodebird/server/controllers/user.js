const userService = require('../services/user-service');

exports.getUserById = async (req, res) => {
  const { id } = req.params;

  try {
    const user = await userService.getUserById(id);
    if (!user) res.status(404).json({ message: 'User not found' });

    res.json(user);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.getUserByEmail = async (req, res) => {
  const { email } = req.params;

  try {
    const user = await userService.getUserByEmail(email);
    if (!user) res.status(404).json({ message: 'User not found' });

    res.json(user);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};
