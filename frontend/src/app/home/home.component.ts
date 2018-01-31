import { Component, OnInit } from '@angular/core';
import {
  ConfigService,
  UserService, DocumentService
} from '../service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  documents: object[];
  currentUser;
  constructor(
    private config: ConfigService,
    private userService: UserService,
    private documentService: DocumentService
  ) { }

  ngOnInit() {
    this.currentUser = this.userService.currentUser;
    this.documentService.getAllDocuments().subscribe((documents) => {
        console.log(documents);
        this.documents = documents;
      });
  }

}
