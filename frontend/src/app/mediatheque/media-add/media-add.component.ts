import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DisplayMessage } from '../../shared/models/display-message';

import {
  MediathequeService
} from '../../service/mediatheque.service';
import { Subject } from 'rxjs/SUbject';

@Component({
  selector: 'app-media-add',
  templateUrl: './media-add.component.html',
  styleUrls: ['./media-add.component.css']
})
export class MediaAddComponent implements OnInit {
  title = 'Ajout d\'une médiathèque';
  githubLink = 'https://github.com/ghilesfeghoul/Springular';
  form: FormGroup;

  /**
   * Boolean used in telling the UI
   * that the form has been submitted
   * and is awaiting a response
   */
  submitted = false;

  /**
   * Notification message from received
   * form request or router
   */
  notification: DisplayMessage;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  constructor (
    private media: MediathequeService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
  }
  ngOnInit() {
    this.route.params
      .takeUntil(this.ngUnsubscribe)
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      name: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
    });
  }


  repository() {
    window.location.href = this.githubLink;
  }

  onSubmit() {
    /**
     * Innocent until proven guilty
     */
    this.notification = undefined;
    this.submitted = true;

    this.media.addMedia(this.form.value)
    // show me the animation
      .delay(1000)
      .subscribe(data => {
          console.log(data);
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.submitted = false;
          console.log('Error on adding media' + JSON.stringify(error));
          this.notification = { msgType: 'error', msgBody: error['error'].errorMessage };
        });

  }
}
