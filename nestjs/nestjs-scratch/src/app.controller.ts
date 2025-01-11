import { Controller, Get } from '@nestjs/common';

@Controller()
export class AppController {
  @Get()
  getRootRoute() {
    return 'hi there';
  }

  @Get('/main')
  getMainRoute() {
    return 'you there';
  }
}
