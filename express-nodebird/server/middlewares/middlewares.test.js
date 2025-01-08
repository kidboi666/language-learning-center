const { isLoggedIn, isNotLoggedIn } = require('../middlewares');

describe('isLoggedIn', () => {
  test('로그인되어 있으면 isLoggedIn이 next를 호출해야 함', () => {
    const req = {
      isAuthenticated: jest.fn(() => true),
    };
    const res = {
      status: jest.fn(),
      send: jest.fn(),
    };
    const next = jest.fn();
    isLoggedIn(req, res, next);
    expect(next).toBeCalledTimes(1);
  });

  test('로그인 되어 있지 않으면 isLoggedIn이 에러를 응답해야 함', () => {});
});

describe('isNotLoggedIn', () => {
  test('로그인되어 있으면 isNotLoggedIn이 에러를 응답해야 함', () => {});

  test('로그인되어 있지 않으면 isNotLoggedIn이 next를 호출해야 함', () => {});
});
