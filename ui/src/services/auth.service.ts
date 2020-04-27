import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {ResponseOrMessage} from '../models/response/response.or.message.model';
import {SignInResponse} from '../models/actor/sign.in.response.model';

@Injectable()
export class AuthService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  signIn(emailOrUserName: string) {
    const url = environment.signIn;
    return this.http.post<ResponseOrMessage<SignInResponse>>(url, {emailOrUserName});
  }

  signUp(email: string, password: string, firstName: string, lastName: string, personType: string) {
    const url = environment.signUp;
    return this.http.post<ResponseOrMessage<boolean>>(url, {email, password, firstName, lastName, personType});
  }

  signOut(id: number) {
    const url = environment.signOut;
    return this.http.post(url, {id});
  }
}
