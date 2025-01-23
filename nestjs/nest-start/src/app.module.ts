import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from './auth/user.entity';
import { Board } from './boards/board.entity';
import { AuthModule } from './auth/auth.module';
import { BoardsModule } from './boards/boards.module';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'localhost',
      port: 5432,
      username: 'jin-wook',
      password: 'free66',
      database: 'board-app',
      entities: [User, Board],
      synchronize: true,
    }),
    AuthModule,
    BoardsModule,
  ],
})
export class AppModule {}
