import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistrationRequest } from '../interfaces/registration-request';
import { ApiResponse } from '../interfaces/api-response';
import { LoginResponse } from '../interfaces/login-response';
import { LoginRequest } from '../interfaces/login-request';
import { Response } from '../interfaces/response';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080/api/v1/user';

  constructor(private http: HttpClient) {}

  register(registrationRequest: RegistrationRequest): Observable<ApiResponse<Response>> {
    const url = `${this.baseUrl}/register`;
    return this.http.post<ApiResponse<Response>>(url, registrationRequest);
  }

  login(loginRequest: LoginRequest): Observable<ApiResponse<LoginResponse>> {
    const url = `${this.baseUrl}/login`;
    return this.http.post<ApiResponse<LoginResponse>>(url, loginRequest);
  }
}
