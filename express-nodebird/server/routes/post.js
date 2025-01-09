const express = require('express');
const router = express.Router();
const { uploadImage, uploadPost } = require('../controllers/post.controller');
const { isLoggedIn } = require('../middlewares');

router.post('/image', isLoggedIn, uploadImage);
router.post('/', isLoggedIn, uploadPost);

module.exports = router;
