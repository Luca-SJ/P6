import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, EMPTY, Observable, switchMap, tap } from 'rxjs';
import { AuthService } from './features/auth/services/auth.service';
import { User } from './interfaces/user.interface';
import { SessionService } from './services/session.service';
import { AuthSuccess } from './features/auth/interfaces/authSuccess.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public onError = false;
  
  constructor(
    private authService: AuthService,
    private router: Router,
    private sessionService: SessionService) {
  }

  public ngOnInit(): void {
    this.autoLog();
  }

  public autoLog(): void {
    if (localStorage.getItem('token') != null) {
      this.authService.me().subscribe({
        next:(user: User) => this.sessionService.user = user,
        error: () => this.sessionService.logOut() 
      })
    }
  }

//   public autoLog(): void {
//     this.authService.me().pipe(tap((response: AuthSuccess) =>{ 
//               this.sessionService.token = response.token;
//               this.router.navigate(['/news']);
//               }),
//           switchMap((response: AuthSuccess) => 
//               this.authService.me().pipe(
//                 tap((user: User) =>{  
//                   this.sessionService.user = user; 
//                   this.sessionService.logIn(user, response.token)
//                 })
//               )
//             ),
//             catchError(() => {
//               this.onError = true;
//               return EMPTY;
//             })
//           ).subscribe();
//   }
}
