import { Module } from '@nestjs/common';
import { BoardsController } from './boards.controller';
import { BoardsService } from './boards.service';
import { DatabaseModule } from '../configs/database.module';
import { boardsProviders } from './boards.providers';

@Module({
  imports: [DatabaseModule],
  providers: [...boardsProviders, BoardsService],
  controllers: [BoardsController],
})
export class BoardsModule {}
