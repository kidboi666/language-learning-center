import db from "../db";

const getUserById = async (id: number) => {
  const query = `SELECT *
                 FROM users
                 WHERE id = $1`;

  return await db.oneOrNone(query, [id]);
};

const getUserByEmail = async (email: string) => {
  const query = `SELECT *
                 FROM users
                 WHERE email = $1`;

  return await db.oneOrNone(query, [email]);
};

export default { getUserById, getUserByEmail };
