import db from '../db';

const uploadPost = async (req, res) => {
  const query = `INSERT INTO posts(content, image, title)
                 VALUES ($1, $2, $3)`;

  const { content, image, title } = req.body;
  await db.none(query, [content, image, title]);
};

module.exports = { uploadPost };
