import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {ApiService, DocumentService, UserService} from '../../service';
import {ConfigService} from '../../service/config.service';
import {EmpruntService} from '../../service/emprunt.service';

@Component({
  selector: 'app-api-card',
  templateUrl: './api-card.component.html',
  styleUrls: ['./api-card.component.scss']
})
export class ApiCardComponent implements OnInit {

  currentUser;
  document;
  media;
  empruntService: EmpruntService;
  documentService: DocumentService;
  @Input() path: string;
  @Input() title: string;
  @Input() author: string;
  @Input() gender: string;
  @Input() year: string;
  @Input() imgUrl: string;
  @Input() tarif: string;

  @Output() apiClick: EventEmitter<any> = new EventEmitter();

  constructor(
  ) {
  }

  ngOnInit() {
   // console.log(this.responseObj);
  }
  realiserEmprunt() {
    this.document = this.documentService.getDocument(this.path);
    this.empruntService.addEmprunt(this.currentUser.id, this.path, this.document.mediatheque.id).subscribe(data => {
      console.log(data)
    })
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
