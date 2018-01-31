import { Component, OnInit } from '@angular/core';
import {DocumentService} from '../../service';

@Component({
  selector: 'app-livre',
  templateUrl: './livre.component.html',
  styleUrls: ['./livre.component.css']
})
export class LivreComponent implements OnInit {
  title = 'Document';
  githubLink = 'https://github.com/ghilesfeghoul/Springular';
  documentService: DocumentService;
  constructor() { }

  ngOnInit() {
  }

  repository() {
    window.location.href = this.githubLink;
  }

}
