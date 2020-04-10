import { Component, OnInit } from '@angular/core';
import {Client} from '../../models/actor/client.model';
import {Driver} from '../../models/actor/driver.model';
import {Router} from '@angular/router';
import {DriverService} from '../../services/driver.service';
import {AdminService} from '../../services/admin.service';
import {DataService} from '../../services/data.service';
import {StoreService} from '../../services/store.service';
import {Trip} from '../../models/trip.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  public tripsArray: Array<Trip>;
  public clientsArray: Array<Client>;
  public driversArray: Array<Driver>;

  constructor(private router: Router, private driverService: DriverService, private adminService: AdminService,
              private dataService: DataService, private storeService: StoreService) {
  }

  ngOnInit(): void {
  }

  showAllTrips() {
    this.adminService.getAllTrips().subscribe(
      data => {
        this.tripsArray = data.body;
      }, error => alert(error)
    );
  }

  showAllClients() {
    this.adminService.getAllDrivers().subscribe(
      data => {
        this.clientsArray = data.body;
      }, error => alert(error)
    );
  }

  showAllDrivers() {
    this.adminService.getAllDrivers().subscribe(
      data => {
        this.driversArray = data.body;
      }, error => alert(error)
    );
  }
}
