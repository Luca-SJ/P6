import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news.service';
import { SessionService } from '../services/session.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {
  data: any;

  constructor(private NewsService: NewsService, private sessionService: SessionService) { }

  ngOnInit(): void {
    this.getDataFromApi();
  }

  logout() {;
    this.sessionService.logOut();
    window.location.href = "/"
  }

  create_news() {
    window.location.href = "/create-news";
  }
  
  getDataFromApi(): void {
    this.NewsService.getNews().subscribe(
      (response) => {
        this.data = response;
        // console.log(this.data);
      },
      (error) => {
        console.error('Erreur lors de la récupération des données', error);
      }
    );
  }
}
