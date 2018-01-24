import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subject} from "rxjs/Subject";
import {UserService} from "../service";
import {DisplayMessage} from "../shared/models/display-message";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  title = 'My Account';
  githubLink = 'https://github.com/bfwg/angular-spring-starter';
  form: FormGroup;
  user = this.userService.currentUser;

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

  constructor(
    private userService: UserService,
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
      id:[this.user.id],
      username: [this.user.username],
      firstname:[this.user.firstname],
      lastname: [this.user.lastname]
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

    this.userService.updateMyInfo(this.form.value)
    // show me the animation
      .delay(1000)
      .subscribe(data => {
          console.log(data);
          this.userService.currentUser.username = this.form.get('username').value;
          this.userService.currentUser.lastname = this.form.get('lastname').value;
          this.userService.currentUser.firstname = this.form.get('firstname').value;
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.submitted = false;
          console.log("Update error" + JSON.stringify(error));
          this.notification = { msgType: 'error', msgBody: error['error'].errorMessage };
        });

  }

}
