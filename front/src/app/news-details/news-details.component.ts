import { Component, OnInit, inject } from '@angular/core';
import { NewsService } from '../news.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-news-details',
  templateUrl: './news-details.component.html',
  styleUrls: ['./news-details.component.scss']
})
export class NewsDetailsComponent implements OnInit {
  newsID!: string;
  data: any;

  constructor(private NewsService: NewsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.newsID = params.get('id')!;
    });
    this.getDataFromApi();
  }

  logout() {
    window.location.href = "/";
  }
  
  getDataFromApi(): void {
    this.NewsService.getNewById(Number(this.newsID)).subscribe(
      (response) => {
        this.data = response;
        // console.log(this.data);
        // console.log(this.newsID);
      },
      (error) => {
        console.error('Erreur lors de la récupération des données', error);
      }
    );
  }

}
