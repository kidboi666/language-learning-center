import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  Post,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { BoardsService } from './boards.service';
import { Board } from './board.entity';
import { CreateBoardDto } from './dto/create-board.dto';
import { UpdateBoardDto } from './dto/update-board.dto';

@Controller('/boards')
export class BoardsController {
  constructor(private boardsService: BoardsService) {}

  @Get(':id')
  getBoardById(@Param('id') id: number): Promise<Board> {
    return this.boardsService.getBoardById(id);
  }

  @Get()
  getBoards(): Promise<Board[]> {
    return this.boardsService.getBoards();
  }

  @Post()
  @UsePipes(ValidationPipe)
  createBoard(@Body() createBoardDto: CreateBoardDto): Promise<Board> {
    return this.boardsService.createBoard(createBoardDto);
  }

  @Patch(':id')
  updateBoard(@Param('id') id: number, @Body() updateBoardDto: UpdateBoardDto): Promise<void> {
    return this.boardsService.updateBoard(id, updateBoardDto);
  }

  @Delete(':id')
  deleteBoardById(@Param('id') id: number): Promise<void> {
    return this.boardsService.deleteBoard(id);
  }
}
