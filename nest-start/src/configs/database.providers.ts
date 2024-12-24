import { DataSource } from 'typeorm';
import { Board } from '../boards/board.entity';
import { User } from '../auth/user.entity';

export const databaseProviders = [
  {
    provide: 'DATA_SOURCE',
    useFactory: async () => {
      const dataSource = new DataSource({
        type: 'postgres',
        host: 'localhost',
        port: 5432,
        username: 'jin-wook',
        password: 'free66',
        database: 'board-app',
        entities: [Board, User],
        synchronize: true,
      });

      return dataSource.initialize();
    },
  },
];
