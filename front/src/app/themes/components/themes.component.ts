import { Component, OnInit } from '@angular/core';
import { ThemeService } from '../services/theme.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {
  data: any;

  constructor(private themeService: ThemeService) { }

  ngOnInit(): void {
    this.getDataFromApi();
  }

  getDataFromApi(): void {
    this.themeService.getThemes().subscribe(
      (response) => {
        this.data = response;
        console.log(this.data);
      },
      (error) => {
        console.error('Erreur lors de la rï¿½cupï¿½ration des donnï¿½es', error);
      }
    );
  }

  public redirectSubscribe(id: Number) {
    this.themeService.subscribe(id).subscribe(response => {alert("abonnement effectué avec succès")}, error => {alert("Vous êtes déjà abonné à ce thème")} );
  };
}
