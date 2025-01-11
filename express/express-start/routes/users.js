const express = require("express");
const router = express.Router();
const User = require("../schemas/user");
/**
 * get all users
 */
router.route('/').get(async (req, res) => {
  try {
    const users = await User.find({});
    res.json(users);
  } catch (err) {
    console.error('Error fetching users:' + err);
    res.status(500).send({message: `Error fetching users ${err.message}`});
  }
})
.post(async (req, res, next) => {
  try {
    await User.create({
          name: req.body.name,
          age: req.body.age,
          married: req.body.married
        });
    res.status(200).send(
        {message: `Successfully created user: ${newUser.name}`});
  } catch (err) {
    console.error('Error creating user:', err);
    res.status(500).send({message: `Error creating user: ${err.message}`});
  }
})

module.exports = router;