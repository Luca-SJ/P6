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
        console.error('Erreur lors de la r�cup�ration des donn�es', error);
      }
    );
  }

  public redirectSubscribe(id: Number) {
    this.themeService.subscribe(id).subscribe(response => {alert("abonnement effectu� avec succ�s")}, error => {alert("Vous �tes d�j� abonn� � ce th�me")} );
  };
}
