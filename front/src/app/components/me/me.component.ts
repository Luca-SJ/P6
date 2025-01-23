import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { TopicService } from 'src/app/topic/services/topic.service';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { catchError, EMPTY, Observable, switchMap, tap } from 'rxjs';
import { Theme } from 'src/app/topic/interfaces/theme.interface';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { UpdateUserInfosRequest } from 'src/app/features/auth/interfaces/UpdateUserInfosRequest.interface';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {
  data: any;
  public user: User | undefined;

  constructor(private route: ActivatedRoute, private topicService: TopicService, private authService: AuthService, private fb: FormBuilder, private sessionService: SessionService, private router: Router) { };

  public form = this.fb.group({
      name: ['', [Validators.required, Validators.min(1)]],
      email: ['', [Validators.required, Validators.min(1)]],
      password: ['', [Validators.required, Validators.min(8)]]
    });

  public ngOnInit(): void {
    this.getSubjects();
  }
  
  public back() {
    window.history.back();
  }

  getSubjects(): void {
    this.user = this.route.snapshot.data['user'];
    this.form.setValue({
      "name": this.user!!.name,
      "email": this.user!!.email,
      "password": null,
    });

    this.topicService.getThemeByUserId(this.user!.id)
    .subscribe({
      next:(response) => this.data = response,
      error:(error)=> console.error('Erreur lors de la récupération des données', error)
      });;
  }

  public redirectUnsubscribe(id: Number) {
    this.topicService.unsubscribe(id).subscribe(response => {
      alert("désabonnement effectué avec succès");
      this.getSubjects();
    }, error => {console.error(error)} );
  };

  public updateUserInfos() {
    const updateUserInfosRequest = this.form.value as UpdateUserInfosRequest;
    this.authService.updateUserInfos(updateUserInfosRequest).subscribe(response => {
      this.sessionService.logOut();
      this.router.navigate(["/"]);
    });
  };

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate([''])
  }
}
