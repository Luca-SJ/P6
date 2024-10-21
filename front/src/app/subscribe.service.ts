import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subscribe } from './subscribe.interface';

@Injectable({
  providedIn: 'root'
})
export class SubscribeService {

  private pathService = 'api/themes/subscribe/';

  constructor(private httpClient: HttpClient) { }

  public getSubscribe(): Observable<Subscribe> {
    return this.httpClient.get<Subscribe>(`${this.pathService}`);
  }
}
