import { Inject, Injectable, NotFoundException } from '@nestjs/common';
import { Repository } from 'typeorm';
import { Board } from './board.entity';
import { CreateBoardDto } from './dto/create-board.dto';
import { BoardStatus } from './dto/board-status-enum';
import { UpdateBoardDto } from './dto/update-board.dto';

@Injectable()
export class BoardsService {
  constructor(
    @Inject('BOARDS_REPOSITORY')
    private readonly boardRepository: Repository<Board>,
  ) {}

  async getBoards(): Promise<Board[]> {
    return this.boardRepository.find();
  }

  async getBoardById(id: number): Promise<Board> {
    const found = await this.boardRepository.findOneBy({ id });

    if (!found) {
      throw new NotFoundException(`can't find a board with id ${id}`);
    }

    return found;
  }

  async createBoard(createBoardDto: CreateBoardDto): Promise<Board> {
    const { title, description } = createBoardDto;

    const board = this.boardRepository.create({
      title,
      description,
      status: BoardStatus.PUBLIC,
    });

    await this.boardRepository.save(board);
    return board;
  }

  async updateBoard(id: number, updateBoardDto: UpdateBoardDto): Promise<void> {
    const { title, description, status } = updateBoardDto;
    const board = await this.boardRepository.findOneBy({ id });

    await this.boardRepository.update(id, {
      title,
      description,
      status: status ? status : board.status,
    });
  }

  async deleteBoard(id: number): Promise<void> {
    await this.boardRepository.delete(id);
  }
}
