export interface ApiResponse<T> {
  timeStamp: string;
  statusCode: number;
  path: string;
  data: T;
  isSuccessful: boolean;
}
