const passport = require('passport');
const KakaoStrategy = require('passport-kakao').Strategy;
const db = require('../db');

module.exports = () => {
  passport.use(
    new KakaoStrategy(
      {
        clientID: process.env.KAKAO_CLIENT_ID,
        callbackURL: '/oauth/kakao/callback',
      },
      async (accessToken, refreshToken, profile, done) => {
        try {
          const selectQuery =
            'select * from users where sns_id = $1 and provider = $2';
          const user = await db.oneOrNone(selectQuery, [profile.id, 'kakao']);

          if (user) {
            done(null, user);
          } else {
            const insertQuery =
              'insert into users(email, name, sns_id, provider) values($1, $2, $3, $4) returning *';
            const newUser = await db.none(insertQuery, [
              profile._json?.kakao_account?.email,
              profile.displayName,
              profile.id,
              'kakao',
            ]);

            done(null, newUser);
          }
        } catch (err) {
          done(err);
        }
      },
    ),
  );
};
