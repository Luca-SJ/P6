import { Component, OnInit } from '@angular/core';
import { AuthService } from '../features/auth/services/auth.service';
import { TopicService } from '../topic/services/topic.service';
import { SessionService } from '../services/session.service';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateArticlesRequest } from '../features/auth/interfaces/createArticlesRequest.interface';
import {ChangeDetectionStrategy} from '@angular/core';
import { ArticlesService } from '../articles.service';
import { User } from '../interfaces/user.interface';
import * as $ from "jquery";

@Component({
  selector: 'app-create-articles',
  templateUrl: './create-articles.component.html',
  styleUrls: ['./create-articles.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreateArticlesComponent {
  topics: any;
  public hide = true;
  public onError = false;
  public user: User | undefined;
  private createArticlesRequest: CreateArticlesRequest = {topic_id: "", title: "", description: "", owner_id: ""};

  public form = this.fb.group({
    topic_id: ['', [Validators.required]],
    title: ['', [Validators.required, Validators.minLength(1)]],
    description: ['', [Validators.required, Validators.min(1)]]
  });
  
  constructor(
    private authService: AuthService, 
    private fb: FormBuilder, 
    private router: Router,
    private route: ActivatedRoute,
    private sessionService: SessionService,
    private topicService: TopicService,
    private articleService: ArticlesService) { }
  
  public ngOnInit(): void {
    this.topicService.getThemes().subscribe({
      next:(response) => {
        this.topics = response
      },
      error:(error)=> console.error('Erreur lors de la récupération des données', error)
    });
  }

  public submit(): void {
    this.user = this.route.snapshot.data['user'];
    this.createArticlesRequest.owner_id=this.user!!.id.toString();
    this.createArticlesRequest.topic_id=this.form.value.topic_id!!;
    this.createArticlesRequest.title=this.form.value.title!!;
    this.createArticlesRequest.description=this.form.value.description!!;

    this.articleService.createArticle(this.createArticlesRequest).subscribe({
      next:(response) => {
        console.log(response);
        $(".modal-title-text").text("Information");
        $(".modal-message-text").text("Article créé avec succès !");
        $(".modal-button-ok").hide();
        $(".modal").fadeIn();

        let modal_redirection_text = $(".modal-redirection").text();
        let i = 4;
        setInterval(function(){
          if (i != 0) {
            $(".modal-redirection").text(modal_redirection_text + i);
            i = i - 1;
          }
        }, 1000);

        setTimeout(
          function() 
          {
            window.location.href = "/articles";
          }, 5000);
      },
      error:(error)=> console.error('Erreur lors de la récupération des données', error)
    });
  }

}
