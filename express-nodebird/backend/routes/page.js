const express = require('express');
const router = express.Router();
const {getUserByEmail, createUser} = require('../controllers/page');

router.get('/user/:email', getUserByEmail)
router.post('/signup', createUser)

module.exports = router;