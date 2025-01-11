import db from "../db";
import bcrypt from "bcrypt";

const checkEmailExists = async (email: string) => {
  const query = "SELECT * FROM users WHERE email = $1";
  const isValid = await db.oneOrNone(query, [email]);
  return !!isValid;
};

const createUser = async (email: string, password: string) => {
  const hash = await bcrypt.hash(password, 12);
  const query = `INSERT INTO users(email, password)
                 VALUES ($1, $2)`;
  await db.none(query, [email, hash]);
};

const updateUser = async (params: {
  id: number;
  nick: string;
  avatarUrl: string;
}) => {
  const { id, nick, avatarUrl } = params;
  const query = `UPDATE users
                 SET nick       = $2,
                     avatar_url = $3
                 WHERE id = $1`;
  await db.none(query, [id, nick, avatarUrl]);
};

const deleteUser = async (id: number) => {
  const query = `DELETE
                 FROM users
                 WHERE id = $1`;
  await db.none(query, [id]);
};

export default { createUser, updateUser, deleteUser, checkEmailExists };
