import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Theme } from './theme.interface';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private pathService = 'api/theme';

  constructor(private httpClient: HttpClient) { }

  public getThemes(): Observable<Theme> {
    return this.httpClient.get<Theme>(`${this.pathService}`);
  }

  public getThemeById(id: number): Observable<Theme> {
    return this.httpClient.get<Theme>(`${this.pathService}/${id}`);
  }
}
