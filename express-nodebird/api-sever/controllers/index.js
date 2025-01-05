exports.test = async (req, res, next) => {
  try {
    if (!req.session.jwt) {
      const tokenResult = await fetch('http://localhost:8002/v1/token', {
        method: 'POST',
        body: JSON.stringify({
          client_secret: process.env.CLIENT_SECRET,
        }),
      })
      if (tokenResult.data?.code === 200) {
        req.session.jwt = tokenResult.data.token;
      } else {
        return res.json(tokenResult.data)
      }
    }

    const result = await fetch('http://localhost:8002/v1/test', {
      method: 'GET',
      headers: {
        "Authorization": req.session.jwt,
      }
    })
    return res.json(result.data)
  } catch (err) {
    console.error(err);
    if(err.response?.status === 419) {
      return res.json(error.response.data);
    }
    return next(err);
  }
}