const db = require('../db');
const bcrypt = require('bcrypt');
const passport = require('passport');

const checkEmailExists = async (email) => {
  const query = 'select * from users where email = $1';

  try {
    const result = await db.oneOrNone(query, [email]);
    return !!result;
  } catch (err) {
    throw new Error(err);
  }
};

const loginUser = async (req, res, next) => {
  passport.authenticate('local', (authError, user, info) => {
    if (authError) {
      console.error(authError);
      return next(authError);
    }
    if (!user) {
      return res.status(401).redirect(`/?error=${info.message}`);
    }
    return req.login(user, (loginError) => {
      if (loginError) {
        console.error(loginError);
        return next(loginError);
      }
      const userInfo = {
        id: user.id,
        email: user.email,
        name: user.name,
        provider: user.provider,
        avatar_url: user.avatar_url,
        created_at: user.created_at,
      };
      return res.status(200).json(userInfo);
    });
  })(req, res, next);
};

const createUser = async (params) => {
  const { email, password } = params;
  const isValid = await checkEmailExists(email, password);
  if (isValid) {
    throw new Error('Email already exists');
  }
  const hash = await bcrypt.hash(password, 12);
  const query = 'insert into users(email, password) values($1, $2)';

  try {
    await db.none(query, [email, hash]);
  } catch (err) {
    throw new Error(err);
  }
};

const updateUser = async (params) => {
  const { id, name, avatarUrl } = params;
  const query = 'update users set name = $2, avatar_url = $3 where id = $1';

  try {
    await db.none(query, [id, name, avatarUrl]);
  } catch (err) {
    throw new Error(err);
  }
};

const deleteUser = async (id) => {
  const query = 'delete from users where id = $1';

  try {
    await db.none(query, [id]);
  } catch (err) {
    throw new Error(err);
  }
};

module.exports = { createUser, loginUser, updateUser, deleteUser };
