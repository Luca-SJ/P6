import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';
import { LoginRequest } from '../../interfaces/loginRequest.interface'; 
import { AuthService } from '../../services/auth.service';
import { concatMap } from 'rxjs';

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

  public submitS(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next:(response: AuthSuccess) => {
        this.router.navigate(['/news'])
        // this.authService.me().subscribe((user: User) => {
        //   this.sessionService.logIn(user, response.token);
        // });
        
      },
      error:() => {
        this.onError = true;
      }
    })
  }

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).pipe(concatMap((response: AuthSuccess) => {
      console.log('Login successful:', response);
      return this.authService.me(); // Appel du second service
    })
    )
    .subscribe({
      next: (userDetails) => {
        console.log('User details:', userDetails);
      },
      error: (err) => {
        console.error('Error occurred:', err);
      },
    });
  }
}