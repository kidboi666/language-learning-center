const pgp = require('pg-promise')();
const fs = require('fs');
const path = require('path');

require('dotenv').config();

const config = {
  host: process.env.DB_HOST,
  port: process.env.DB_PORT,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_DATABASE,
}

const db = pgp(config);

const runSQLFile = async (filePath) => {
  try {
    const sql = fs.readFileSync(filePath, 'utf8');
    await db.none(sql);
    console.log('SQL query successfully.');
  } catch (error) {
    console.error(error);
  }
}

const initDB = async () => {
  try {
  const initSQLPath = path.join(__dirname, 'init.sql');
  await runSQLFile(initSQLPath);
  console.log('DB initialized!');
  } catch(err) {
    console.error(err);
  }
}

initDB()

module.exports = db;