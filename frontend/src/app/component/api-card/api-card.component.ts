import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {ApiService} from '../../service';
import {ConfigService} from '../../service/config.service';

@Component({
  selector: 'app-api-card',
  templateUrl: './api-card.component.html',
  styleUrls: ['./api-card.component.scss']
})
export class ApiCardComponent implements OnInit {

  @Input() path: string;
  @Input() title: string;
  @Input() author: string;
  @Input() gender: string;
  @Input() year: string;
  @Input() imgUrl: string;
  @Input() tarif: string;


  @Output() apiClick: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit() {
   // console.log(this.responseObj);
  }
  onEmpruntClick() {
    this.apiClick.next(this.path)
  }

  /* responsePanelClass() {
    const rClass = ['response'];
    if (this.expand) {
      rClass.push('expand');
    }
    if (this.responseObj.status) {
      this.responseObj.status === 200 ?
        rClass.push('response-success') :
        rClass.push('response-error');
    }
    return rClass.join(' ');
  } */

}
