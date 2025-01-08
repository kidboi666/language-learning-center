const db = require('../db');
const jwt = require('jsonwebtoken');

const findDomain = async (clientSecret) => {
  const query = `SELECT d.*, u.nick, u.id
                 FROM domain d
                          LEFT JOIN users u ON d.user_id = u.id
                 WHERE d.client_secret = $1
                 LIMIT 1`;
  return await db.oneOrNone(query, [clientSecret]);
};

const createToken = (id, nick) => {
  return jwt.sign(
    {
      id,
      nick,
    },
    process.env.JWT_SECRET,
    {
      expiresIn: '1m', // 1분
      issuer: 'nodebird',
    },
  );
};

const getByPosts = async (id) => {
  const query = `SELECT *
                 FROM posts
                 WHERE user_id = $1`;
  return await db.manyOrNone(query, [id]);
};

const getPostsByHashtag = async (tag) => {
  const query = `SELECT p.*
                 FROM posts p
                          INNER JOIN post_hashtags ph ON p.id = ph.post_id
                          INNER JOIN hashtags h ON ph.hashtag_id = h.id
                 WHERE h.title = $1`;
  return await db.any(query, [tag]);
};

module.exports = { findDomain, createToken, getByPosts, getPostsByHashtag };
