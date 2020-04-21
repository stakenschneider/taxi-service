import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  getDataForTrips: any = {};
  parametersForTrips: Map<string, any> = new Map<string, any>();

  getDataForClient: any = {};
  parametersForClient: Map<string, any> = new Map<string, any>();

  getDataForDriver: any = {};
  parametersForDriver: Map<string, any> = new Map<string, any>();

  constructor() {
  }

  ngOnInit(): void {
    this.getDataForTrips.dataName = 'tripTable';
    this.parametersForTrips.set('for', 'ADMIN');
    this.getDataForTrips.parameters = this.parametersForTrips;

    this.getDataForClient.dataName = 'clientTable';

    this.getDataForDriver.dataName = 'driverTable';
  }
}
