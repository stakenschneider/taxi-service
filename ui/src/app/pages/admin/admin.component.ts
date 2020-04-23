import {Component, OnInit} from '@angular/core';
import {UiGridData} from '../../../models/table/ui.grid.data.model';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  getDataForTrips: any = {};
  parametersForTrips: Map<string, any> = new Map<string, any>();
  uiGridDataForTrips: UiGridData = new UiGridData();

  getDataForClient: any = {};
  parametersForClient: Map<string, any> = new Map<string, any>();
  uiGridDataForClient: UiGridData = new UiGridData();

  getDataForDriver: any = {};
  parametersForDriver: Map<string, any> = new Map<string, any>();
  uiGridDataForDriver: UiGridData = new UiGridData();

  constructor() {
  }

  ngOnInit(): void {
    this.uiGridDataForTrips.pageSizeOptions = [3, 5, 7];
    this.uiGridDataForTrips.showFilter = true;
    this.uiGridDataForTrips.showPaginator = true;
    this.getDataForTrips.dataName = 'tripTable';
    this.parametersForTrips.set('for', 'ADMIN');
    this.parametersForTrips.set('page', '0');
    this.parametersForTrips.set('size', '5');
    this.parametersForTrips.set('sortBy', 'price');
    this.getDataForTrips.parameters = this.parametersForTrips;

    this.uiGridDataForClient.showFilter = false;
    this.uiGridDataForClient.showPaginator = false;
    this.getDataForClient.dataName = 'clientTable';
    this.parametersForClient.set('', '');
    this.getDataForClient.parameters = this.parametersForClient;

    this.getDataForDriver.dataName = 'driverTable';
    this.parametersForDriver.set('', '');
    this.getDataForDriver.parameters = this.parametersForDriver;
  }

  onPaginatorClick(event: PageEvent) {
    this.parametersForTrips.set('page', event.pageIndex);
    this.parametersForTrips.set('size', event.pageSize);
    this.parametersForTrips.set('sortBy', 'name');
    this.getDataForTrips.parameters = this.parametersForTrips;
  }
}
