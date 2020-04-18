import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Person} from '../models/actor/person.model';
import {ResponseOrMessage} from '../models/response/response.or.message.model';
import {Trip} from '../models/trip.model';

@Injectable()
export class DataService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  getPaymentMethod() {
    const url = environment.getPaymentMethod;
    return this.http.get<any>(url, {});
  }

  getRate() {
    const url = environment.getRate;
    return this.http.get<any>(url, {});
  }

  getCarColorList() {
    const url = environment.getCarColor;
    return this.http.get<any>(url, {});
  }

  getCarModelList() {
    const url = environment.getCarModels;
    return this.http.get<any>(url, {});
  }

  getPersonById(personId: number, personType: string) {
    const url = environment.getPersonById;
    return this.http.post<ResponseOrMessage<Person>>(url, {personId, personType});
  }

  getHistoryOfTips(id: number) {
    const url = environment.getHistoryOfTips;
    return this.http.post<ResponseOrMessage<Array<Trip>>>(url, {id});
  }
}
