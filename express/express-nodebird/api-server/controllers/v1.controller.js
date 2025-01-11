const v1Service = require('../services/v1.service');

exports.createToken = async (req, res) => {
  const { clientSecret } = req.body;

  try {
    const domain = await v1Service.findDomain(clientSecret);

    if (!domain) {
      return res.status(401).json({
        code: 401,
        message: '등록되지 않은 도메인입니다. 먼저 도메인을 등록하세요',
      });
    }
    const token = v1Service.createToken(domain.user.id, domain.user.nick);
    return res.json({
      code: 200,
      message: '토큰이 발급되었습니다',
      token,
    });
  } catch (error) {
    console.error(error);
    return res.status(500).json({
      code: 500,
      message: '서버 에러',
    });
  }
};

exports.tokenTest = (req, res) => {
  res.json(res.locals.decoded);
};

exports.getMyPosts = async (req, res) => {
  try {
    const posts = await v1Service.getMyPosts(req.locals.decoded.id);
    return res.json({
      code: 200,
      payload: posts,
    });
  } catch (err) {
    console.error(err);
    return res.status(500).json({
      code: 500,
      message: '서버 에러',
    });
  }
};

exports.getPostsByHashtag = async (req, res) => {
  try {
    const posts = await v1Service.getPostsByHashtag(req.params.title);
    if (posts.length === 0) {
      return res.status(404).json({
        code: 404,
        message: '검색 결과가 없습니다.',
      });
    }
  } catch (err) {
    console.error(err);
  }
};
