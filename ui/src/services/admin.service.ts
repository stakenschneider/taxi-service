import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {ApiResult} from '../models/response/api.result.model';

@Injectable()
export class AdminService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  deletePerson(personId: number, personType: string) {
    const url = environment.deletePerson + '/' + personId + '/' + personType;
    return this.http.delete<ApiResult>(url, {});
  }

  generate(count: number) {
    const url = environment.generate + '/' + count;
    return this.http.get<ApiResult>(url, {});
  }
}
