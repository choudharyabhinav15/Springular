import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { AdminComponent } from './admin';
import { LoginGuard } from './guard';
import { GuestGuard, AdminGuard } from './guard';
import { NotFoundComponent } from './not-found';
import { ChangePasswordComponent } from './change-password';
import { ForbiddenComponent } from './forbidden';
import { SignupComponent } from './signup';
import {AccountComponent} from './account';
import {MediaAddComponent} from './mediatheque/media-add';
import {DocAllComponent} from './document/doc-all';
import {EmpAllComponent} from './emprunt/emp-all';
import {LivreComponent} from './document/livre';
import {EmpruntComponent} from './emprunt/emprunt/emprunt.component';
export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'signup',
    component: SignupComponent,
    canActivate: [GuestGuard],
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'my-account',
    component: AccountComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'change-password',
    component: ChangePasswordComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'document/all',
    component: DocAllComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'emprunt/all',
    component: EmpAllComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'document',
    component: LivreComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'media',
    component: MediaAddComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'emprunt',
    component: EmpruntComponent,
    canActivate: [LoginGuard]
  },
  {
    path: '404',
    component: NotFoundComponent
  },
  {
    path: '403',
    component: ForbiddenComponent
  },
  {
    path: '**',
    redirectTo: '/404'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
