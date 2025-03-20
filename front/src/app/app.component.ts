import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './features/auth/services/auth.service';
import { User } from './interfaces/user.interface';
import { SessionService } from './services/session.service';
import * as $ from "jquery";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public onError = false;
  public show = true;

  constructor(
    private authService: AuthService,
    private router: Router,
    private sessionService: SessionService) {
  }

  public ngOnInit(): void {
    this.autoLog();

    $(".modal-button-ok").click(function() {
      $(".modal").fadeOut();
    });

  }

  public checkConnection(): boolean {
    return localStorage.getItem('token') ? true : false;
  }

  public autoLog(): void {
    if (localStorage.getItem('token') != null) {
      this.authService.me().subscribe({
        next:(user: User) => this.sessionService.user = user,
        error: () => this.sessionService.logOut() 
      })
    }
  }

}
