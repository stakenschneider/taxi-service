import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {GetData} from '../models/table/get.data.model';
import {GridData} from '../models/table/grid.data.model';
import {Observable} from 'rxjs';

@Injectable()
export class GridDataService {
  protected readonly http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  getGridData(getDataModel: GetData): Observable<GridData> {
    const url = environment.getGridData;
    return this.http.post<GridData>(url, {getDataModel});
  }
}
