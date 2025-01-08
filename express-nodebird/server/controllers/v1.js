const jwt = require('jsonwebtoken');
const db = require('../db');

exports.createToken = async (req, res) => {
  const { clientSecret } = req.body;
  const query = `SELECT d.*, u.name, u.id
                 FROM domain d
                          JOIN users u
                               ON d.user_id = u.id
                 WHERE d.client_secret = $1`;
  try {
    const domain = await db.oneOrNone(query, [clientSecret]);

    if (!domain) {
      return res.status(401).json({
        code: 401,
        message: 'No token found',
      });
    }

    const token = jwt.sign(
      {
        id: domain.user_id,
        name: domain.user_name,
      },
      process.env.JWT_SECRET,
      {
        expiresIn: '1m',
        issuer: 'nodebird',
      },
    );
    return res.json({
      code: 200,
      message: 'Successfully created token',
      token,
    });
  } catch (err) {
    console.error(err);
    return res.status(500).json({
      code: 500,
      message: 'Internal Server Error',
    });
  }
};

exports.tokenTest = async (req, res) => {
  try {
  } catch (err) {}
};
