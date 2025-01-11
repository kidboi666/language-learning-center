import passport from "passport";
import { Strategy as LocalStrategy } from "passport-local";
import bcrypt from "bcrypt";
import passportService from "../services/passport.service";

export default () => {
  passport.use(
    new LocalStrategy(
      {
        usernameField: "email",
        passwordField: "password",
      },
      async (email, password, done) => {
        try {
          const user = await passportService.getUserByEmail(email);

          if (user) {
            const result = await bcrypt.compare(password, user.password);
            if (result) {
              done(null, user);
            } else {
              done(null, false, { message: "Passwords do not match" });
            }
          } else {
            return done(null, false, { message: "Email does not exist" });
          }
        } catch (err) {
          console.error(err);
          done(err);
        }
      },
    ),
  );
};
