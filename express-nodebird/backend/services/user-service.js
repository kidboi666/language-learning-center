const db = require('../db');

const getUserById = async (id) => {
  const query = 'select * from users where id = $1';

  try {
    return await db.any(query, [id]);
  } catch (err) {
    throw new Error(err);
  }
};

const getUserByEmail = async (email) => {
  const query = 'select * from users where email = $1';

  try {
    return await db.any(query, [email]);
  } catch (err) {
    throw new Error(err);
  }
};

module.exports = { getUserById, getUserByEmail };
