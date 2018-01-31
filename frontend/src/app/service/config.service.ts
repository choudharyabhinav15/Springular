import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable()
export class ConfigService {

  private _api_url = '/api';

  private _refresh_token_url = this._api_url + '/refresh';

  private _login_url = this._api_url + '/login';

  private _logout_url = this._api_url + '/logout';

  private _change_password_url = this._api_url + '/changePassword';

  private _whoami_url = this._api_url + '/whoami';

  private _user_url = this._api_url + '/user';

  private _users_url = this._user_url + '/all';

  private _reset_credentials_url = this._user_url + '/reset-credentials';

  private _signup_url = this._api_url + '/signup';

  private _account_url = this._api_url + '/user/my-account';

  private _document_url = this._api_url + '/document';

  private _documents_url = this._document_url + '/all';

  private _mediatheque_url = this._api_url + '/media';

  private _mediatheques_url = this._mediatheque_url + '/s';

  private _emprunt_url = this._api_url + '/emprunt';
  private  _emprunts_url = this._emprunt_url + '/all';
  get reset_credentials_url(): string {
      return this._reset_credentials_url;
  }

  get refresh_token_url(): string {
      return this._refresh_token_url;
  }

  get whoami_url(): string {
      return this._whoami_url;
  }

  get users_url(): string {
      return this._users_url;
  }

  get login_url(): string {
      return this._login_url;
  }

  get logout_url(): string {
      return this._logout_url;
  }

  get change_password_url(): string {
      return this._change_password_url;
  }
  get signup_url(): string {
      return this._signup_url;
  }

  get account_url(): string {
    return this._account_url;
  }

  get documents_url(): string {
    return this._documents_url;
  }
  get mediatheques_url(): string {
    return this._mediatheques_url;
  }

  get mediatheque_url(): string {
    return this._mediatheque_url;
  }

  get document_url(): string {
    return this._document_url;
  }

  get emprunt_url(): string {
    return this._emprunt_url;
  }

  get emprunts_url(): string {
    return this._emprunts_url;
  }
}
