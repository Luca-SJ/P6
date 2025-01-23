import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { AuthSuccess  } from '../interfaces/authSuccess.interface';
import { RegisterRequest } from '../interfaces/registerRequest.interface';
import { User } from 'src/app/interfaces/user.interface';
import { environment } from 'src/environments/environment';
import { UpdateUserInfosRequest } from '../interfaces/UpdateUserInfosRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private pathService = environment.baseUrl;

  constructor(private httpClient: HttpClient) { }

  public register(registerRequest: RegisterRequest): Observable<AuthSuccess> {
    return this.httpClient.post<AuthSuccess>(`${this.pathService}auth/register`, registerRequest);
  }

  public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    return this.httpClient.post<AuthSuccess>(`${this.pathService}auth/login`, loginRequest);
  }

  public me(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}auth/me`);
  }

  public updateUserInfos(updateUserInfos: UpdateUserInfosRequest): Observable<User> {
    return this.httpClient.put<User>(`${this.pathService}auth/me`, updateUserInfos);
  }
}
