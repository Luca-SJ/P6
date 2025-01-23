import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { Observable, of } from 'rxjs';
import { AuthService } from '../features/auth/services/auth.service';
import { User } from '../interfaces/user.interface';
import { SessionService } from '../services/session.service';


@Injectable({
  providedIn: 'root'
})
export class UserResolver implements Resolve<User> {
  
  constructor(
    private authService: AuthService,
    private session: SessionService
  ) {}

  resolve(): Observable<User> {
    if (this.session.user) {
      return of(this.session.user);
    }
    return this.authService.me();
  }
}
