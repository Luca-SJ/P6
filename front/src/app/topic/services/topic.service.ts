import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Theme } from '../interfaces/theme.interface';
import { User } from 'src/app/interfaces/user.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private pathService = environment.baseUrl + 'topics';

  constructor(private httpClient: HttpClient) { }

  public getThemes(): Observable<Theme> {
    return this.httpClient.get<Theme>(`${this.pathService}`);
  }

  public getThemeByUserId(id: number): Observable<Theme> {
    return this.httpClient.get<Theme>(`${this.pathService}/user/${id}`);
  }

  public subscribe(id: Number): Observable<Subscription> {
    return this.httpClient.post<Subscription>(`${this.pathService}/subscribe/` + id, "");
  }

  public unsubscribe(id: Number): Observable<Subscription> {
    return this.httpClient.delete<Subscription>(`${this.pathService}/subscribe/` + id);
  }

}
