import local from "./local-strategy";
import passport from "passport";
import kakao from "./kakao-strategy";
import passportService from "../services/passport.service";

export default () => {
  passport.serializeUser((user, done) => {
    done(null, user.id);
  });

  passport.deserializeUser(async (id, done) => {
    try {
      const user = await passportService.getUserById(Number(id));
      done(null, user);
    } catch (err) {
      done(err);
    }
  });

  local();
  kakao();
};
