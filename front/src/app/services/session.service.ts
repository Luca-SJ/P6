import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from '../features/auth/services/auth.service';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private _user: User | undefined;

  public isLogged(): Boolean {
    return localStorage.getItem("token")?true:false;
  }

  set user(user:User|undefined){
       this._user = user;
  }

  get user():User|undefined{
       return this._user;
  }

  set token(token:string|null){
    if (token) {
      localStorage.setItem('token', token);
    }
   }

  public logIn(user: User, token: string): void {
    this.user = user;
    if (token) {
      localStorage.setItem('token', token);
    }
    
  }

  public logOut(): void {
    localStorage.removeItem('token');
    this._user = undefined;
  }
}
