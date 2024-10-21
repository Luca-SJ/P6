import { Component, OnInit } from '@angular/core';
import { ThemeService } from '../theme.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {
  data: any;

  constructor(private ThemeService: ThemeService) { }

  ngOnInit(): void {
    this.getDataFromApi();
  }

  getDataFromApi(): void {
    this.ThemeService.getThemes().subscribe(
      (response) => {
        this.data = response;
        console.log(this.data);
      },
      (error) => {
        console.error('Erreur lors de la récupération des données', error);
      }
    );
  }

  redirectSubscribe() {
    
  }
}
