import userService from "../services/user.service";
import { RequestHandler } from "express";

export const getUserById: RequestHandler = async (req, res) => {
  const { id } = req.params;

  try {
    const user = await userService.getUserById(Number(id));
    if (!user) res.status(404).json({ message: "유저를 찾을 수 없습니다." });

    res.json(user);
  } catch (err) {
    if (err instanceof Error) {
      res.status(500).json({ message: err.message });
    }
    res.status(500).json({ message: "알 수 없는 에러가 발생했습니다." });
  }
};

export const getUserByEmail: RequestHandler = async (req, res) => {
  const { email } = req.params;

  try {
    const user = await userService.getUserByEmail(email);
    if (!user) res.status(404).json({ message: "유저를 찾을 수 없습니다." });

    res.json(user);
  } catch (err) {
    if (err instanceof Error) {
      res.status(500).json({ message: err.message });
    }
    res.status(500).json({ message: "알 수 없는 에러가 발생했습니다." });
  }
};
