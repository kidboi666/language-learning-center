import { Module } from '@nestjs/common';
import { AuthController } from './auth.controller';
import { AuthService } from './auth.service';
import { DatabaseModule } from '../configs/database.module';
import { authProviders } from './auth.providers';

@Module({
  imports: [DatabaseModule],
  providers: [...authProviders, AuthService],
  controllers: [AuthController],
})
export class AuthModule {}
