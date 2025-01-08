const db = require('../db');
const bcrypt = require('bcrypt');

const checkEmailExists = async (email) => {
  const query = 'SELECT * FROM users WHERE email = $1';
  await db.oneOrNone(query, [email]);
};

const createUser = async (req, res, next, params) => {
  const { email, password } = params;
  const isValid = await checkEmailExists(email, password);

  if (!!isValid) {
    const error = new Error('Email already exists');
    error.status = 401;
    throw error;
  }

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

module.exports = { createUser, updateUser, deleteUser };
