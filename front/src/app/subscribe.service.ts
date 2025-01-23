import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subscribe } from './subscribe.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SubscribeService {

  private pathService = environment.baseUrl + 'themes/subscribe/';

  constructor(private httpClient: HttpClient) { }

  public getSubscribe(): Observable<Subscribe> {
    return this.httpClient.get<Subscribe>(`${this.pathService}`);
  }
}
