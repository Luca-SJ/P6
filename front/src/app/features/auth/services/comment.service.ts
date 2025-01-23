import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SubmitCommentRequest } from '../interfaces/SubmitCommentRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private pathService = environment.baseUrl;

  constructor(private httpClient: HttpClient) { }

  public postComment(submitCommentRequest: SubmitCommentRequest): Observable<SubmitCommentRequest> {
    return this.httpClient.post<SubmitCommentRequest>(`${this.pathService}comments`, submitCommentRequest);
  }

  public getCommentsByNewId(articleID: String) {
    return this.httpClient.get<Comment>(`${this.pathService}comments/article/${articleID}`);
  }
}
