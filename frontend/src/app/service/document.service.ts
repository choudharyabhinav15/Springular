import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';

@Injectable()
export class DocumentService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  getAllDocuments() {
    return this.apiService.get(this.config.documents_url);
  }

}
