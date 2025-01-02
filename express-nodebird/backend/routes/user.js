const express = require('express');
const router = express.Router();
const { getUserById, getUserByEmail } = require('../controllers/user');

router.get('/:id', getUserById);
router.get('/:email', getUserByEmail);

module.exports = router;
