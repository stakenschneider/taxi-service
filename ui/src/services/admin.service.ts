import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {ResponseOrMessage} from '../models/response.or.message.model';
import {Trip} from '../models/trip.model';
import {Client} from '../models/actor/client.model';
import {Driver} from '../models/actor/driver.model';
import {ApiResult} from '../models/api.result.model';

@Injectable()
export class AdminService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  getAllTrips() {
    const url = environment.getAllTrips;
    return this.http.get<ResponseOrMessage<Array<Trip>>>(url, {});
  }

  getAllClients() {
    const url = environment.getAllClients;
    return this.http.get<ResponseOrMessage<Array<Client>>>(url, {});
  }

  getAllDrivers() {
    const url = environment.getAllDrivers;
    return this.http.get<ResponseOrMessage<Array<Driver>>>(url, {});
  }

  deletePerson(personId: number, personType: string) {
    const url = environment.deletePerson + '/' + personId + '/' + personType;
    return this.http.delete<ApiResult>(url, {});
  }
}
