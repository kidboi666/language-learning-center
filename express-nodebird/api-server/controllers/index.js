const db = require('../db');

exports.renderLogin = async (req, res, next) => {
  const query =
    'SELECT u.*, json_agg(d) as domains FROM users u LEFT JOIN domain d ON d.user_id = u.id WHERE u.id = $1 GROUP BY u.id';
  try {
    const user = await db.oneOrNone(query, [req.user?.id || null]);
    console.log(user);
    res.render('login', {
      user,
      domains: user?.domains,
    });
  } catch (err) {
    console.error(err);
    next(err);
  }
};

exports.createDomain = async (req, res, next) => {
  const query = 'INSERT INTO domain(user_id, host, type) VALUES ($1, $2, $3)';
  try {
    await db.none(query, [req.user?.id, req.body.host, req.body.type]);
    res.redirect('/');
  } catch (err) {
    console.error(err);
    next(err);
  }
};
