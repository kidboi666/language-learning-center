import { BadRequestException, PipeTransform } from '@nestjs/common';
import { BoardStatus } from '../dto/board-status-enum';

export class BoardStatusValidationPipe implements PipeTransform {
  readonly StatusOptions = [BoardStatus.PRIVATE, BoardStatus.PUBLIC];

  transform(value: any) {
    value = value.toLowerCase();

    if (!this.isStatusValid(value)) {
      throw new BadRequestException(`can't get board with id ${value}`);
    }

    return value;
  }

  private isStatusValid(status: any) {
    const index = this.StatusOptions.indexOf(status);
    return index !== -1;
  }
}
