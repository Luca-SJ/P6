import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Articles } from './articles.interface';
import { environment } from 'src/environments/environment';
import { User } from './interfaces/user.interface';
import { CreateArticlesRequest } from './features/auth/interfaces/createArticlesRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  private pathService = environment.baseUrl + 'articles';

  constructor(private httpClient: HttpClient) { }

  public getArticles(): Observable<Articles> {
    return this.httpClient.get<Articles>(`${this.pathService}`);
  }

  public getNewById(id: string): Observable<Articles> {
    return this.httpClient.get<Articles>(`${this.pathService}/${id}`);
  }

  public getNewByUserId(id: number): Observable<Articles[]> {
    return this.httpClient.get<Articles[]>(`${this.pathService}/user/${id}`);
  }

  public getOwnerNameById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${environment.baseUrl}topics/user/${id}`);
  }

  public createArticle(createArticlesRequest: CreateArticlesRequest): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}`, createArticlesRequest);
  }

}
