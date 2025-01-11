import express from "express";
import { getUserById, getUserByEmail } from "../controllers/user.controller";

const router = express.Router();

router.get("/:id", getUserById);
router.get("/:email", getUserByEmail);

export default router;
