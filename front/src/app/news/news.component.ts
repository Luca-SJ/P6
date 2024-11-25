import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { NewsService } from '../news.service';
import { SessionService } from '../services/session.service';
import { User } from '../interfaces/user.interface';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {
  data: any;
  public user: User | undefined;

  constructor(private authService: AuthService, private NewsService: NewsService, private sessionService: SessionService) { }

  ngOnInit(): void {
    this.authService.me().subscribe(
      (user: User) => { 
        this.user = user;
        this.getDataFromApi();
      }
    );
  }

  logout() {;
    this.sessionService.logOut();
    window.location.href = "/"
  }

  create_news() {
    window.location.href = "/create-news";
  }
  
  getDataFromApi(): void {
    this.NewsService.getNewByUserId(this.user!.id).subscribe(
      (response) => {
        this.data = response;
        // console.log(this.data);
      },
      (error) => {
        console.error('Erreur lors de la r�cup�ration des donn�es', error);
      }
    );
  }
}
