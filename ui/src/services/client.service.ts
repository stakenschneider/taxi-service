import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {ApiResult} from '../models/api.result.model';
import {ResponseOrMessage} from '../models/response.or.message.model';
import {Trip} from '../models/trip.model';

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

  getActiveTrip(clientId: number) {
    const url = environment.getActiveTrip;
    return this.http.post<ResponseOrMessage<Trip>>(url, {clientId});
  }

  setGrade(tripId: number, grade: number) {
    const url = environment.setGrade;
    return this.http.post<ApiResult>(url, {tripId, grade});
  }
}
