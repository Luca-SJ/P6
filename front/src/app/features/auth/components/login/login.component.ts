import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';
import { LoginRequest } from '../../interfaces/loginRequest.interface'; 
import { AuthService } from '../../services/auth.service';
import { catchError, concatMap, EMPTY, switchMap, tap } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent  {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    nameOrEmail: ['', [Validators.required, Validators.min(1)]],
    password: ['', [Validators.required, Validators.min(3)]]
  });

  constructor(private authService: AuthService, 
    private fb: FormBuilder, 
    private router: Router,
    private sessionService: SessionService) { }

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).pipe(
      tap((response: AuthSuccess) =>{ 
        this.sessionService.token = response.token;
        this.router.navigate(['/articles']);
        }),
    switchMap((response: AuthSuccess) => 
        this.authService.me().pipe(
          tap((user: User) =>{  
            this.sessionService.user = user; 
            this.sessionService.logIn(user, response.token)
          })
        )
      ),
      catchError(() => {
        this.onError = true;
        return EMPTY;
      })
    ).subscribe();  
  }


}