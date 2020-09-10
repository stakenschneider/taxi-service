import {Component, OnInit} from '@angular/core';
import {UiGridData} from '../../../models/table/ui.grid.data.model';

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
    this.uiGridDataForTrips.showFilter = true;
    this.uiGridDataForTrips.showPaginator = true;
    this.uiGridDataForTrips.cellsIsEditable = true;

    this.getDataForTrips.dataName = 'tripTable';
    this.parametersForTrips.set('for', 'ADMIN');
    this.getDataForTrips.parameters = this.parametersForTrips;

    this.uiGridDataForClient.showFilter = false;
    this.uiGridDataForClient.showPaginator = true;
    this.getDataForClient.dataName = 'clientTable';
    this.parametersForClient.set('', '');
    this.getDataForClient.parameters = this.parametersForClient;

    this.getDataForDriver.dataName = 'driverTable';
    this.parametersForDriver.set('', '');
    this.getDataForDriver.parameters = this.parametersForDriver;
  }
}
