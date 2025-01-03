exports.afterUploadImage = (req, res) => {
  console.log(req);
  res.json({ url: `/image/${req.file.filename}` });
};

exports.uploadPost = (req, res, next) => {
  console.log(req);

  try {
  } catch (err) {
    console.error(err);
    next();
  }
};
