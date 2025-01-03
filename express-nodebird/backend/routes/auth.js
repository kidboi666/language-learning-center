const express = require('express');
const router = express.Router();
const {
  updateUser,
  createUser,
  login,
  checkSession,
  logout,
} = require('../controllers/auth');
const { isNotLoggedIn, isLoggedIn } = require('../middlewares');

router.patch('/:id', isLoggedIn, updateUser);
router.post('/signup', isNotLoggedIn, createUser);
router.post('/login', isNotLoggedIn, login);
router.post('/logout', isLoggedIn, logout);
router.get('/session', checkSession);

module.exports = router;
