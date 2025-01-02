export class ServiceError extends Error {
  constructor(message) {
    super(message);
    this.name = 'CustomError';
    this.status = 400;
  }
}
