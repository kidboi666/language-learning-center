export type ActionResponse<T> =
  | { status: 'success'; data: T }
  | { status: 'error'; error: { message: string } };
