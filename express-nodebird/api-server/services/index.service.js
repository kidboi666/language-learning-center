const db = require('../db');
const { v4: uuidv4 } = require('uuid');

const renderLogin = async (id) => {
  const query = `SELECT *
                 FROM users u
                          LEFT JOIN domain d
                                    ON u.id = d.user_id
                 WHERE u.id = $1`;
  return await db.oneOrNone(query, [id]);
};

const createDomain = async (id, host, type) => {
  const query = `INSERT INTO domain(user_id, host, type, client_secret)
                 VALUES ($1, $2, $3, $4)`;
  return await db.none(query, [id, host, type, uuidv4()]);
};

module.exports = { renderLogin, createDomain };
