import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';

@Controller() // 컨트롤러 데코레이터
export class AppController {
  // 외부에서 사용하므로 export를 붙여준다.
  constructor(private readonly appService: AppService) {}

  @Get() // get 요청 처리 데코레이터
  getHello(): string {
    return this.appService.getHello();
  }
}
