const db = require('../db')

const getUserById = async (id) => {
  try {
    return await db.any('select * from users where id = $1', [id]);
  } catch (err) {
    throw new Error(err);
  }
}

const createUser = async (params) => {
  const {email, password} = params;

  try {
    await db.none(
        'insert into users(email, password) values($1, $2)', [email, password]);
  } catch (err) {
    throw new Error(err);
  }
}

module.exports = {getUserById, createUser}