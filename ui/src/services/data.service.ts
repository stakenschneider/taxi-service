import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Observable} from 'rxjs';

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
}
