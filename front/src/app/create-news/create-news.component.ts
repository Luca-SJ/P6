import { Component, OnInit } from '@angular/core';
import { AuthService } from '../features/auth/services/auth.service';
import { SessionService } from '../services/session.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../features/auth/interfaces/loginRequest.interface';
import { AuthSuccess } from '../features/auth/interfaces/authSuccess.interface';
import { User } from '../interfaces/user.interface';
import { CreateNewsRequest } from '../features/auth/interfaces/createNewsRequest.interface';

@Component({
  selector: 'app-create-news',
  templateUrl: './create-news.component.html',
  styleUrls: ['./create-news.component.scss']
})
export class CreateNewsComponent {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    topic: ['', [Validators.required, Validators.minLength(1)]],
    titre: ['', [Validators.required, Validators.minLength(1)]],
    contenu: ['', [Validators.required, Validators.min(1)]]
  });
  
  constructor(private authService: AuthService, 
    private fb: FormBuilder, 
    private router: Router,
    private sessionService: SessionService) { }

  public submit(): void {
    const createNewsRequest = this.form.value as CreateNewsRequest;

    // this.authService.login(loginRequest).subscribe(
    //   (response: AuthSuccess) => {
    //     localStorage.setItem('token', response.token);
    //     this.authService.me().subscribe((user: User) => {
    //       this.sessionService.logIn(user);
    //       this.router.navigate(['/news'])
    //     });
    //     this.router.navigate(['/'])
    //   },
    //   error => this.onError = true
    // );
  }

}
