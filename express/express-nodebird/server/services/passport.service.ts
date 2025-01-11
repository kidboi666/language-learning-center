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

const getUserBySnsId = async (snsId: number, provider: Provider) => {
  const query = `SELECT *
                 FROM users
                 WHERE sns_id = $1
                   AND provider = $2`;
  return await db.oneOrNone(query, [snsId, provider]);
};

const createOAuthUser = async (
  email: string,
  nick: string,
  snsId: number,
  provider: Provider,
) => {
  const query = `INSERT INTO users(email, nick, sns_id, provider)
                 VALUES ($1, $2, $3, $4)
                 RETURNING *`;
  return await db.one(query, [email, nick, snsId, provider]);
};

const passportService = {
  getUserById,
  getUserByEmail,
  getUserBySnsId,
  createOAuthUser,
};

export default passportService;
