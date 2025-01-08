const postService = require('../services/post-service');

exports.afterUploadImage = (req, res) => {
  res.json({ url: `/image/${req.file.filename}` });
};

exports.uploadPost = async (req, res, next) => {
  try {
    await postService.uploadPost(req, res);
    res.status(200).send();
  } catch (err) {
    console.error(err);
    next(err);
  }
};
