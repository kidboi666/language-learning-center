const { isLoggedIn, isNotLoggedIn, verifyToken } = require('../middlewares');
const res = require('express/lib/response');

describe('isLoggedIn', () => {
  const res = {
    status: jest.fn(() => res),
    send: jest.fn(),
    locals: {
      decoded: 'token1',
    },
    json: jest.fn(() => {}),
  };
  const next = jest.fn();

  test('로그인되어 있으면 isLoggedIn이 next를 호출해야 함', () => {
    const req = {
      isAuthenticated: jest.fn(() => true),
    };
    isLoggedIn(req, res, next);
    expect(next).toBeCalledTimes(1);
  });

  test('로그인 되어 있지 않으면 isLoggedIn이 에러를 응답해야 함', () => {
    const req = {
      isAuthenticated: jest.fn(() => false),
    };
    isLoggedIn(req, res, next);
    expect(res.status).toBeCalledWith(403);
    expect(res.send).toBeCalledWith('Not Authenticated');
  });

  test('로그인 되어 있고 토큰이 일치하지 않을때 에러를 응답해야 함', () => {
    const req = {
      headers: {
        authorization: 'token2',
      },
    };
    verifyToken(req, res, next);
    expect(res.status).toBeCalledWith(401);
    expect(res.json).toBeCalledWith({ message: 'Unauthorized' });
  });
});

describe('isNotLoggedIn', () => {
  const res = {
    status: jest.fn(() => res),
    send: jest.fn(),
    locals: {
      decoded: 'token1',
    },
    json: jest.fn(() => {}),
  };
  const next = jest.fn();

  test('로그인되어 있으면 isNotLoggedIn이 에러를 응답해야 함', () => {
    const req = {
      isAuthenticated: jest.fn(() => true),
    };
    isNotLoggedIn(req, res, next);
    expect(res.status).toBeCalledWith(403);
    expect(res.send).toBeCalledWith('Already Authenticated');
  });

  test('로그인되어 있지 않으면 isNotLoggedIn이 next를 호출해야 함', () => {
    const req = {
      isAuthenticated: jest.fn(() => false),
    };
    isNotLoggedIn(req, res, next);
    expect(next).toBeCalledTimes(1);
  });
});
