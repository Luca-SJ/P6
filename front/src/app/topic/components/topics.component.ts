import { Component, OnInit } from '@angular/core';
import { TopicService } from '../services/topic.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-themes',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicComponent implements OnInit {
  data: any;

  constructor(private topicService: TopicService) { }

  ngOnInit(): void {
    this.getDataFromApi();
  }

  getDataFromApi(): void {
    this.topicService.getThemes().subscribe(
      (response) => {
        this.data = response;
      },
      (error) => {
        console.error('Erreur lors de la r�cup�ration des donn�es', error);
      }
    );
  }

  public redirectSubscribe(id: Number) {
    this.topicService.subscribe(id).subscribe(response => {alert("abonnement effectu� avec succ�s")}, error => {alert("Vous �tes d�j� abonn� � ce th�me")} );
  };
}
