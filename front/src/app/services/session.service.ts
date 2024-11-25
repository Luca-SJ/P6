import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from '../features/auth/services/auth.service';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public user: User | undefined;

  public isLogged(): Boolean {
    return localStorage.getItem("token")?true:false;
  }

  public logIn(user: User, token: string): void {
    this.user = user;
    if (token) {
      localStorage.setItem('token', token);
    }
    
  }

  public logOut(): void {
    localStorage.removeItem('token');
    this.user = undefined;
  }
}
