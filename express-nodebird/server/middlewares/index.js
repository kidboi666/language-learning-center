const jwt = require('jsonwebtoken');

exports.isLoggedIn = (req, res, next) => {
  if (req.isAuthenticated()) {
    next();
  } else {
    return res.status(403).send('Not Authenticated');
  }
};

exports.isNotLoggedIn = (req, res, next) => {
  if (!req.isAuthenticated()) {
    next();
  } else {
    return res.status(403).send('Already Authenticated');
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
    if (err.name === 'TokenExpired') {
      return res.status(419).json({
        message: 'Token expired',
      });
    }

    return res.status(401).json({
      message: 'Unauthorized',
    });
  }
};
