const db = require('../db');
const bcrypt = require('bcrypt');
const passport = require('passport');

const checkEmailExists = async (email) => {
  const query = 'select * from users where email = $1';

  return await db.oneOrNone(query, [email]);
};

const createUser = async (req, res, next, params) => {
  const { email, password } = params;

  const isValid = await checkEmailExists(email, password);

  if (!!isValid) {
    const error = new Error('Email already exists');
    error.status = 401;
    throw error;
  }

  const hash = bcrypt.hash(password, 12);
  const query = 'insert into users(email, password) values($1, $2)';

  await db.none(query, [email, hash]);
};

const updateUser = async (params) => {
  const { id, name, avatarUrl } = params;
  const query = 'update users set name = $2, avatar_url = $3 where id = $1';

  await db.none(query, [id, name, avatarUrl]);
};

const deleteUser = async (id) => {
  const query = 'delete from users where id = $1';

  await db.none(query, [id]);
};

module.exports = { createUser, updateUser, deleteUser };
