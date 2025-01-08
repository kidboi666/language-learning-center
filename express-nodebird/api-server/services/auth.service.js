const db = require('../db');
const bcrypt = require('bcrypt');

const createUser = async (email, nick, password) => {
  const query = `INSERT INTO users(email, nick, password)
                 VALUES ($1, $2, $3)`;
  const hash = await bcrypt.hash(password, 12);

  await db.none(query, [email, nick, hash]);
};

const getUserByEmail = async (email) => {
  const query = `SELECT *
                 FROM users
                 WHERE email = $1`;

  return await db.oneOrNone(query, [email]);
};

module.exports = { createUser, getUserByEmail };
