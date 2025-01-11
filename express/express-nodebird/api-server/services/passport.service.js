const db = require('../db');

const getUserById = async (id) => {
  const query = `SELECT *
                 FROM users
                 WHERE id = $1`;
  return await db.oneOrNone(query, [id]);
};

const getUserByEmail = async (email) => {
  const query = `SELECT *
                 FROM users
                 WHERE email = $1`;
  return await db.oneOrNone(query, [email]);
};

const getUserBySnsId = async (snsId, provider) => {
  const query = `SELECT *
                 FROM users
                 WHERE sns_id = $1
                   AND provider = $2`;
  return await db.oneOrNone(query, [snsId, provider]);
};

const createOAuthUser = async (email, nick, snsId, provider) => {
  const query = `INSERT INTO users(email, nick, sns_id, provider)
                 VALUES ($1, $2, $3, $4)
                 RETURNING *`;
  return await db.one(query, [email, nick, snsId, provider]);
};

module.exports = {
  getUserById,
  getUserByEmail,
  getUserBySnsId,
  createOAuthUser,
};
