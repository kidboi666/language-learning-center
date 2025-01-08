const indexService = require('../services/index-service');

exports.renderLogin = async (req, res, next) => {
  try {
    const user = await indexService.renderLogin(req.user?.id || null);
    res.render('login', {
      user,
      domains: user?.domain,
    });
  } catch (err) {
    console.error(err);
    next(err);
  }
};

exports.createDomain = async (req, res, next) => {
  try {
    await indexService.createDomain(req.user.id, req.body.host, req.body.type);
    res.redirect('/');
  } catch (err) {
    console.error(err);
    next(err);
  }
};
