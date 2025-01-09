const db = require('../db');
const bcrypt = require('bcrypt');

const checkEmailExists = async (email) => {
  const query = 'SELECT * FROM users WHERE email = $1';
  const isValid = await db.oneOrNone(query, [email]);
  return !!isValid;
};

const createUser = async (email, password) => {
  const hash = await bcrypt.hash(password, 12);
  const query = `INSERT INTO users(email, password)
                 VALUES ($1, $2)`;
  await db.none(query, [email, hash]);
};

const updateUser = async (params) => {
  const { id, nick, avatarUrl } = params;
  const query = `UPDATE users
                 SET nick       = $2,
                     avatar_url = $3
                 WHERE id = $1`;
  await db.none(query, [id, nick, avatarUrl]);
};

const deleteUser = async (id) => {
  const query = `DELETE
                 FROM users
                 WHERE id = $1`;
  await db.none(query, [id]);
};

module.exports = { createUser, updateUser, deleteUser, checkEmailExists };
