import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Person} from '../models/person.model';

@Injectable()
export class AuthService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  signIn(email: string) {
    const url = environment.signIn;
    return this.http.post<any>(url, {email});
  }

  signUp(email: string, password: string, firstName: string, lastName: string) {
    const url = environment.signUp;
    return this.http.post<Person>(url, {email, password, firstName, lastName});
  }

  // signOut(id: number, personType: string) {
  //   const url = environment.signOut;
  //   return this.http.post(url, {id, personType});
  // }
}
