import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Person} from '../models/actor/person.model';
import {ResponseOrMessage} from '../models/response.or.message.model';

@Injectable()
export class AuthService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  signIn(email: string) {
    const url = environment.signIn;
    // на самом деле это не почта а почта или юсернейм
    return this.http.post<ResponseOrMessage<Person>>(url, {email});
  }

  signUp(email: string, password: string, firstName: string, lastName: string) {
    const url = environment.signUp;
    return this.http.post<boolean>(url, {email, password, firstName, lastName});
  }

  // signOut(id: number, personType: string) {
  //   const url = environment.signOut;
  //   return this.http.post(url, {id, personType});
  // }
}
