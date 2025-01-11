import pgPromise, { IDatabase, IMain } from "pg-promise";

import fs from "fs";
import path from "path";
import { IClient } from "pg-promise/typescript/pg-subset";
import { config } from "dotenv";

config();

const pgp: IMain = pgPromise();
const dbInit = {
  host: process.env.DB_HOST!,
  port: Number(process.env.DB_PORT!),
  user: process.env.DB_USER!,
  password: process.env.DB_PASSWORD!,
  database:
    process.env.NODE_ENV === "test"
      ? process.env.TEST_DB_DATABASE!
      : process.env.DB_DATABASE!,
};

const db: IDatabase<{}, IClient> = pgp(dbInit);

const runSQLFile = async (filePath: string) => {
  try {
    const sql = fs.readFileSync(filePath, "utf8");
    await db.none(sql);
    console.log("SQL query successfully.");
  } catch (error) {
    console.error(error);
  }
};

const initDB = async () => {
  try {
    const initSQLPath = path.join(__dirname, "init.sql");
    await runSQLFile(initSQLPath);
    console.log("DB initialized!");
  } catch (err) {
    console.error(err);
  }
};

void initDB();

export default db;
