import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {HttpHeaders} from '@angular/common/http';

@Injectable()
export class MediathequeService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  getAllMedia() {
    return this.apiService.get(this.config.mediatheques_url);
  }

  addMedia(mediatheque) {
    const mediaHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    console.log(mediatheque);
    return this.apiService.post(this.config.mediatheque_url, JSON.stringify(mediatheque), mediaHeaders).map(() => {
      console.log('Media added successfully');
    });
  }
  remove(id) {
    console.log('Removing the media which id is ' + id);
    return this.apiService.delete(this.config.mediatheque_url + '/' + id);
  }

}
