import { Injectable } from '@angular/core';
import {ConfigService} from './config.service';
import {ApiService} from './api.service';

@Injectable()
export class EmpruntService {

  emprunts;
  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  getAllEmprunt() {
    return this.apiService.get(this.config.emprunts_url).subscribe((emprunts) => {
      console.log(emprunts);
      this.emprunts = emprunts
    });
  }
  getEmprunt(id) {
    return this.apiService.get(this.config.emprunt_url + '/' + id);
  }
  addEmprunt(idUser, idDoc, idMedia) {
    return this.apiService.get(this.config.emprunt_url + '/' + idUser + '/' + idDoc + '/' + idMedia);
  }
}
