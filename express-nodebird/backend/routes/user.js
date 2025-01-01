const express = require('express');
const router = express.Router();
const { getUserById, getUserByEmail } = require('../controllers/user');

router.get('/user/:id', getUserById);
router.get('/user/:email', getUserByEmail);

module.exports = router;
