import { Component, OnInit } from '@angular/core';
import {ApiService, ConfigService, DocumentService} from '../../service';

@Component({
  selector: 'app-doc-all',
  templateUrl: './doc-all.component.html',
  styleUrls: ['./doc-all.component.css']
})
export class DocAllComponent implements OnInit {

  title = 'Liste des documents';
  githubLink = 'https://github.com/ghilesfeghoul/Springular';
  documents;
  constructor(
    private documentService: DocumentService
  ) { }

  ngOnInit() {
    this.documents = this.documentService.getAllDocuments();
  }

  repository() {
    window.location.href = this.githubLink;
  }

}
