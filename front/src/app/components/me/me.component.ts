import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { ThemeService } from 'src/app/themes/services/theme.service';
import { User } from 'src/app/interfaces/user.interface';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {
  data: any;
  public user: User | undefined;

  constructor(private authService: AuthService, private themeService: ThemeService) { };

  public ngOnInit(): void {
    this.authService.me().subscribe(
      (user: User) => { 
        this.user = user;
        this.getDataFromApi();
      }
    );
    
  }

  public back() {
    window.history.back();
  }

  getDataFromApi(): void {
    this.themeService.getThemeByUserId(this.user!.id).subscribe(
      (response) => {
        this.data = response;
        console.log(this.data);
      },
      (error) => {
        console.error('Erreur lors de la rï¿½cupï¿½ration des donnï¿½es', error);
      }
    );
  }

  public redirectUnsubscribe(id: Number) {
    this.themeService.unsubscribe(id).subscribe(response => {
      alert("désabonnement effectué avec succès");
      this.getDataFromApi();
    }, error => {console.error(error)} );
  };
}
