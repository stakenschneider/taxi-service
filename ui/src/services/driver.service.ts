import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {ApiResult} from '../models/response/api.result.model';
import {ResponseOrMessage} from '../models/response/response.or.message.model';
import {Trip} from '../models/trip.model';

@Injectable()
export class DriverService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  setCar(driverId: number, carNumber: string, model: string, color: string) {
    const url = environment.setCar;
    return this.http.post<ApiResult>(url, {driverId, carNumber, model, color});
  }

  setPassport(id: number, series: string, number: string) {
    const url = environment.setPassport;
    return this.http.post<ApiResult>(url, {id, series, number});
  }

  getCurrentTrip(id: number) {
    const url = environment.getCurrentTrip;
    return this.http.post<ResponseOrMessage<Trip>>(url, {id});
  }

  reserveTrip(driverId: number, tripId: number) {
    const url = environment.takeTrip;
    return this.http.post<ApiResult>(url, {driverId, tripId});
  }

  endTrip(grade: number, tripId: number) {
    const url = environment.endTrip;
    return this.http.post<ApiResult>(url, {grade, tripId});
  }
}
