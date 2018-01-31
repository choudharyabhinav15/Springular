import { Component, OnInit } from '@angular/core';
import {EmpruntService} from '../../service/emprunt.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder} from '@angular/forms';
import {UserService} from '../../service';

@Component({
  selector: 'app-emp-all',
  templateUrl: './emp-all.component.html',
  styleUrls: ['./emp-all.component.css']
})
export class EmpAllComponent implements OnInit {
  title = 'Liste de mes emprunts';
  githubLink = 'https://github.com/ghilesfeghoul/Springular';
  emprunts;
  constructor(
    private empruntService: EmpruntService,
  ) {}

  ngOnInit() {
    this.emprunts = this.empruntService.getAllEmprunt();
  }

  repository() {
    window.location.href = this.githubLink;
  }
}
