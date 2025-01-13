import { Controller, Get, Patch, Post } from '@nestjs/common';

@Controller('reports')
export class ReportsController {
  @Get()
  getReports() {}

  @Post()
  createReports() {}

  @Patch(':id')
  updateReports() {}
}
