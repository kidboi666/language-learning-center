const express = require('express');
const router = express.Router();
const {
  updateUser,
  createUser,
  login,
  checkSession,
  logout,
} = require('../controllers/auth');

router.patch('/:id', updateUser);
router.post('/signup', createUser);
router.post('/login', login);
router.post('/logout', logout);
router.get('/session', checkSession);

module.exports = router;
