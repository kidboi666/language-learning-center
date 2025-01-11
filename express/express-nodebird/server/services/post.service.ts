import db from "../db";
import fs from "fs";
import multer from "multer";
import path from "path";

try {
  fs.readdirSync("uploads");
} catch (err) {
  fs.mkdirSync("uploads");
}

const upload = multer({
  storage: multer.diskStorage({
    destination: (req, file, cb) => {
      cb(null, "uploads/");
    },
    filename: (req, file, cb) => {
      console.log(file);
      const ext = path.extname(file.originalname);
      cb(null, path.basename(file.originalname, ext) + Date.now() + ext);
    },
  }),
  limits: { fileSize: 5 * 1024 * 1024 },
});

const uploadImage = () => upload.single("image");

const uploadPost = async (
  title: string,
  content: string,
  image: string,
  userId: number,
) => {
  const query = `INSERT INTO posts(title, content, image, user_id)
                 VALUES ($1, $2, $3, $4)`;

  await db.none(query, [title, content, image, userId]);
};

export default { uploadImage, uploadPost };
