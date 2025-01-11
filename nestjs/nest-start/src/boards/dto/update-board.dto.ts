import { BoardStatus } from './board-status-enum';
import { IsIn, IsOptional } from 'class-validator';

export class UpdateBoardDto {
  @IsOptional()
  title: string;

  @IsOptional()
  description: string;

  @IsIn([BoardStatus.PUBLIC, BoardStatus.PUBLIC], {
    message: 'status는 private 또는 public 이어야 합니다.',
  })
  status?: BoardStatus;
}
