const postService = require('../services/post.service');

exports.uploadImage = (req, res, next) => {
  try {
    postService.uploadImage();
    res.json({ url: `/image/${req.file.filename}` });
  } catch (err) {
    console.error(err);
    next(err);
  }
};

exports.uploadPost = async (req, res, next) => {
  const { title, content, image, userId } = req.body;

  if (!title) {
    return res.status(400).send('Empty title');
  }
  if (!content) {
    return res.status(400).send('Empty content');
  }
  if (!userId) {
    return res.status(400).send('Empty userId');
  }

  try {
    await postService.uploadPost(title, content, image, userId);
    res.status(201).send();
  } catch (err) {
    console.error(err);
    next(err);
  }
};
