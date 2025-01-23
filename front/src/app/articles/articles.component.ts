import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { ArticlesService } from '../articles.service';
import { SessionService } from '../services/session.service';
import { User } from '../interfaces/user.interface';
import { Articles } from '../articles.interface';
import { HttpErrorResponse } from '@angular/common/http';
import { forkJoin, map } from 'rxjs';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  data: any;
  public user: User | undefined;
  articles: Articles[] = [];
  sortedArticles: Articles[] = [];
  selectedSort: string = "desc"; // Filtrage des articles par date de création décroissante par défaut

  constructor(private authService: AuthService, private articlesService: ArticlesService, private sessionService: SessionService) { }

  ngOnInit(): void {
    this.authService.me().subscribe(
      (user: User) => { 
        this.user = user;
        this.getDataFromApi();
      }
    );
  }

  create_articles() {
    window.location.href = "/create-articles";
  }

  getDataFromApi(): void {
    this.articlesService.getNewByUserId(this.user!.id).subscribe({
      next: (response: Articles[]) => {
        this.data = response;
        console.log(this.data);
        this.sortDesc();
      },
      error: (error: HttpErrorResponse) => {
        console.error('Erreur lors de la récupération des données', error.message);
      }
    });
  }

  // Méthode permettant de filtrer de manière croissante les articles
  sortAsc(): void {
    this.data = this.data.sort((a: Articles, b: Articles) => new Date(a.created_at).getTime() - new Date(b.created_at).getTime());
  }

  // Méthode permettant de filtrer de manière décroissante les articles
  sortDesc(): void {
    this.data = this.data.sort((a: Articles, b: Articles) => new Date(b.created_at).getTime() - new Date(a.created_at).getTime());
  }

  sort(){
    switch(this.selectedSort) {
      // Si l'option <Décroissant> est choisi
      case "desc":
        this.sortDesc();
        break;

      // Si l'option <Croissant> est choisi
      case "asc":
        this.sortAsc();
        break;
    }
  }
}
