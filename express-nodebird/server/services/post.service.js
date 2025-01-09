const db = require('../db');
const fs = require('fs');
const multer = require('multer');
const path = require('path');

try {
  fs.readdirSync('uploads');
} catch (err) {
  fs.mkdirSync('uploads');
}

const upload = multer({
  storage: multer.diskStorage({
    destination: (req, file, cb) => {
      cb(null, 'uploads/');
    },
    filename: (req, file, cb) => {
      console.log(file);
      const ext = path.extname(file.originalname);
      cb(null, path.basename(file.originalname, ext) + Date.now() + ext);
    },
  }),
  limits: { fileSize: 5 * 1024 * 1024 },
});

const uploadImage = () => upload.single('image');

const uploadPost = async (title, content, image, userId) => {
  const query = `INSERT INTO posts(title, content, image, user_id)
                 VALUES ($1, $2, $3, $4)`;

  await db.none(query, [title, content, image, userId]);
};

module.exports = { uploadImage, uploadPost };
