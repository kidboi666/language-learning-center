import passport from "passport";
import { Profile, Strategy as KakaoStrategy } from "passport-kakao";
import passportService from "../services/passport.service";

export default () => {
  passport.use(
    new KakaoStrategy(
      {
        clientID: process.env.KAKAO_CLIENT_ID!,
        callbackURL: "/oauth/kakao/callback",
      },
      async (
        accessToken: string,
        refreshToken: string,
        profile: Profile,
        done: any,
      ) => {
        try {
          const user = await passportService.getUserBySnsId(
            Number(profile.id),
            Provider.KAKAO,
          );
          if (user) {
            done(null, user);
          } else {
            const newUser = await passportService.createOAuthUser(
              profile._json?.kakao_account?.email,
              profile.displayName,
              Number(profile.id),
              Provider.KAKAO,
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
