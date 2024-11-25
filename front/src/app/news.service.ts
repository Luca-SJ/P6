import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { News } from './news.interface';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  private pathService = 'api/news';

  constructor(private httpClient: HttpClient) { }

  public getNews(): Observable<News> {
    return this.httpClient.get<News>(`${this.pathService}`);
  }

  public getNewById(id: number): Observable<News> {
    return this.httpClient.get<News>(`${this.pathService}/${id}`);
  }

  public getNewByUserId(id: number): Observable<News> {
    return this.httpClient.get<News>(`${this.pathService}/user/${id}`);
  }

}
