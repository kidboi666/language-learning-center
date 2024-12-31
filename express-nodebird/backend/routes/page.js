const express = require('express');
const router = express.Router();
const {getUserById, createUser} = require('../controllers/page');

router.get('/user/:id', getUserById)
router.post('/signup', createUser)

module.exports = router;