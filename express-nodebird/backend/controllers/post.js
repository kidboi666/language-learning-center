const db = require('../db');

exports.afterUploadImage = (req, res) => {
  res.json({ url: `/image/${req.file.filename}` });
};

exports.uploadPost = async (req, res, next) => {
  const query = 'insert into posts(content, image, title) values($1, $2, $3)';

  try {
    const { content, image, title } = req.body;
    await db.none(query, [content, image, title]);
    res.status(200).send();
  } catch (err) {
    console.error(err);
    next();
  }
};
