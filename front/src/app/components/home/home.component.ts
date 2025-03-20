import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {
    $("mat-toolbar").hide();
  }

  register() {
    window.location.href = "/register";
  }

  login() {
    window.location.href = "/login";
  }
}
