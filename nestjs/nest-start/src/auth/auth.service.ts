import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { AuthCredentialsDto } from './dto/auth-credentials.dto';
import { User } from './user.entity';
import { InjectRepository } from '@nestjs/typeorm';

@Injectable()
export class AuthService {
  constructor(
    @InjectRepository(User)
    private authRepository: Repository<User>,
  ) {}

  async signUp(authCredentialsDto: AuthCredentialsDto): Promise<void> {
    const { username, password } = authCredentialsDto;
    const user = this.authRepository.create({ username, password });

    await this.authRepository.save(user);
  }

  async getUserByUsername(username: string): Promise<User> {
    return this.authRepository.findOneBy({ username });
  }
}
