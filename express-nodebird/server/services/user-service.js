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

module.exports = { getUserById, getUserByEmail };
