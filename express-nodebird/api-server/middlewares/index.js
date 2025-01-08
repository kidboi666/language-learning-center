const jwt = require('jsonwebtoken');
const rateLimit = require('express-rate-limit');
const db = require('../db');

exports.isLoggedIn = (req, res, next) => {
  if (req.isAuthenticated()) {
    next();
  } else {
    res.status(403).send('로그인 필요');
  }
};

exports.isNotLoggedIn = (req, res, next) => {
  if (!req.isAuthenticated()) {
    next();
  } else {
    const message = encodeURIComponent('로그인한 상태입니다.');
    res.redirect(`/?error=${message}`);
  }
};

exports.verifyToken = (req, res, next) => {
  try {
    res.locals.decoded = jwt.verify(
      req.headers.authorization,
      process.env.JWT_SECRET,
    );
    return next();
  } catch (err) {
    if (err.name === 'TokenExpiredError') {
      return res.status(419).json({
        code: 419,
        message: 'TokenExpired Error',
      });
    }
    return res.status(401).json({
      code: 401,
      message: 'Unauthorized',
    });
  }
};

exports.apiLimiter = async (req, res, next) => {
  let user;
  if (res.locals.decoded.id) {
    const query = `SELECT *
                   FROM users
                   WHERE id = $1`;
    user = await db.oneOrNone(query, [res.locals.decoded.id]);
  }

  rateLimit({
    windowMs: 60 * 1000,
    max: user?.type === 'premium' ? 1000 : 10,
    handler(req, res) {
      res.status(this.statusCode).json({
        code: this.statusCode,
        message: '1분에 열 번만 요청할 수 있습니다.',
      });
    },
  })(req, res, next);
};

exports.deprecated = (req, res) => {
  res.status(410).json({
    code: 410,
    message: '새로운 버전이 나왔습니다. 새로운 버전을 사용하세요.',
  });
};
