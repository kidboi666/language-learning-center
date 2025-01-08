const passport = require('passport');
const KakaoStrategy = require('passport-kakao').Strategy;
const passportService = require('../services/passport-service');

module.exports = () => {
  passport.use(
    new KakaoStrategy(
      {
        clientID: process.env.KAKAO_CLIENT_ID,
        callbackURL: '/oauth/kakao/callback',
      },
      async (accessToken, refreshToken, profile, done) => {
        try {
          const user = await passportService.getUserBySnsId(
            profile.id,
            'kakao',
          );
          if (user) {
            done(null, user);
          } else {
            const newUser = await passportService.createOAuthUser(
              profile._json?.kakao_account?.email,
              profile.displayName,
              profile.id,
              'kakao',
            );

            done(null, newUser);
          }
        } catch (err) {
          done(err);
        }
      },
    ),
  );
};
