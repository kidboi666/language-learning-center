const db = require('../db')

const getUserByEmail = async (email) => {
  try {
    return await db.any('select * from users where email = ?', [email]);
  } catch (err) {
    throw new Error(err);
  }
}

const createUser = async (params) => {
  const {email, password} = params;

  try {
    await db.none(
        'INSERT INTO users(email, password) VALUES($1, $2)', [email, password]);
  } catch (err) {
    throw new Error(err);
  }
}

module.exports = {getUserByEmail, createUser}