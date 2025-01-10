import { RequestHandler } from "express";
import passport from "passport";
import authService from "../services/auth.service";

export const login: RequestHandler = (req, res, next) => {
  passport.authenticate(
    "local",
    (
      authError: Error | null,
      user: Express.User,
      info: { message: string },
    ) => {
      if (authError) {
        return next(authError);
      }

      if (!user) {
        return res.status(403).send(info.message);
      }

      return req.login(user, (loginError) => {
        if (loginError) {
          return next(loginError);
        }

        return res.json(user);
      });
    },
  )(req, res, next);
};

export const logout: RequestHandler = async (req, res, next) => {
  if (req.isAuthenticated()) {
    req.logout(() => {
      res.status(204).send();
    });
  } else {
    res.status(403).send("로그인을 안한 상태입니다.");
  }
};

export const createUser: RequestHandler = async (req, res, next) => {
  const { email, password } = req.body;

  try {
    const isValid = await authService.checkEmailExists(email);
    if (isValid) {
      const error = new Error("가입된 이메일이 있습니다.");
      error.status = 403;
      throw error;
    }
    await authService.createUser(email, password);
    res.status(201).send();
  } catch (err) {
    if (err instanceof Error) {
      res.status(err.status).send(err.message);
    }
    res.status(500).json({ message: "알 수 없는 에러가 발생했습니다." });
  }
};

export const updateUser: RequestHandler = async (req, res, next) => {
  const { id } = req.params;
  const { name, avatarUrl } = req.body;

  try {
    await authService.updateUser({
      id: Number(id),
      nick: name,
      avatarUrl,
    });
    res.send();
  } catch (err) {
    next(err);
  }
};

export const checkSession: RequestHandler = async (req, res, next) => {
  if (req.isAuthenticated()) {
    res.json({ isLoggedIn: true, user: req.user });
  } else {
    res.json({ isLoggedIn: false });
  }
};
