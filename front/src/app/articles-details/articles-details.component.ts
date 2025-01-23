import { Component, OnInit, inject } from '@angular/core';
import { ArticlesService } from '../articles.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { SubmitCommentRequest } from '../features/auth/interfaces/SubmitCommentRequest.interface';
import { CommentService } from '../features/auth/services/comment.service';
import { User } from '../interfaces/user.interface';
import { AuthService } from '../features/auth/services/auth.service';
import { SessionService } from '../services/session.service';

@Component({
  selector: 'app-articles-details',
  templateUrl: './articles-details.component.html',
  styleUrls: ['./articles-details.component.scss']
})
export class ArticlesDetailsComponent implements OnInit {
  data: any;
  comments: any;
  public user: User | undefined;
  private submitCommentRequest: SubmitCommentRequest = {message: "", user_id: "", article_id: ""};

  public form = this.fb.group({
      message: ['', [Validators.required]],
    });

  constructor(
    private commentService: CommentService,
    private articlesService: ArticlesService,
    private route: ActivatedRoute,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.user = this.route.snapshot.data['user'];
    this.route.paramMap.subscribe((params) => {
      this.submitCommentRequest.user_id=this.user!!.id.toString();
      this.submitCommentRequest.article_id=params.get('id')!;
    });

    this.getDataFromApi();
  }

  logout() {
    window.location.href = "/";
  }
  
  getDataFromApi(): void {
    this.articlesService.getNewById(this.submitCommentRequest.article_id).subscribe({
      next: (response) => {
        this.data = response;
        // console.log(this.data);
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des données', error);
      }
    });
    this.commentService.getCommentsByNewId(this.submitCommentRequest.article_id).subscribe({
      next: (response) => {
        this.comments = response;
        // console.log(response);
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des données', error);
      }
    });
    
  }

  public submitComment(): void {
    this.submitCommentRequest.message = this.form.value['message']!;
    this.commentService.postComment(this.submitCommentRequest).subscribe({
      next: () => {
        this.getDataFromApi();
      }
    });
  }

}
