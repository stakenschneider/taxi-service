import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Address} from '../models/address.model';
import {ApiResult} from '../models/api.result.model';

@Injectable()
export class ClientService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  requestCar(clientId: number, startAddress, finishAddress, paymentMethod: string, rate: string) {
    const url = environment.requestCar;
    return this.http.post<ApiResult>(url, {clientId, startAddress, finishAddress, paymentMethod, rate});
  }

  denyTrip(id: number) {
    const url = environment.denyTrip;
    return this.http.post<ApiResult>(url, {id});
  }
}
