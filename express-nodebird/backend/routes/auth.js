const express = require('express');
const router = express.Router();
const { updateUser, createUser, login } = require('../controllers/auth');

router.patch('/:id', updateUser);
router.post('/signup', createUser);
router.post('/login', login);

module.exports = router;
