import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {GetData} from '../models/table/get.data.model';
import {GridData} from '../models/table/grid.data.model';
import {Observable} from 'rxjs';
import {ResponseOrMessage} from '../models/response/response.or.message.model';

@Injectable()
export class GridDataService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  getGridData(getDataModel: GetData): Observable<ResponseOrMessage<GridData>> {
    const parameters = {};
    getDataModel.parameters.forEach((val: string, key: string) => {
      parameters[key] = val;
    });
    const dataName = getDataModel.dataName;
    const url = environment.getGridData;
    return this.http.post<ResponseOrMessage<GridData>>(url, {dataName, parameters});
  }
}
