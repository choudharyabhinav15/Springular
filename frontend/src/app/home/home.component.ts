import { Component, OnInit } from '@angular/core';
import {
  FooService,
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
    private fooService: FooService,
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

  makeRequest(path) {
    console.log(path)
  }
}
