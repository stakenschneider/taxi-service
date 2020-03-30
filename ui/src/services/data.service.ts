import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Observable} from 'rxjs';
import {Person} from '../models/actor/person.model';
import {ResponseOrMessage} from '../models/response.or.message.model';
import {Trip} from '../models/trip.model';

@Injectable()
export class DataService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  // tslint:disable-next-line:ban-types
  getPaymentMethod(): Observable<Object> {
    const url = environment.getPaymentMethod;
    return this.http.get<any>(url, {});
  }

  getRate() {
    const url = environment.getRate;
    return this.http.get<any>(url, {});
  }

  getPersonById(id: number) {
    const url = environment.getPersonById;
    return this.http.post<ResponseOrMessage<Person>>(url, {id});
  }

  getHistoryOfTips(id: number) {
    const url = environment.getHistoryOfTips;
    return this.http.post<ResponseOrMessage<Array<Trip>>>(url, {id});
  }
}
