import {Component, OnInit, ViewChild} from '@angular/core';
import {Client} from '../../models/actor/client.model';
import {Driver} from '../../models/actor/driver.model';
import {Router} from '@angular/router';
import {DriverService} from '../../services/driver.service';
import {AdminService} from '../../services/admin.service';
import {DataService} from '../../services/data.service';
import {StoreService} from '../../services/store.service';
import {MatTableDataSource} from '@angular/material/table';
import {Trip} from '../../models/trip.model';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  public clientsArray: Client[];
  public driversArray: Driver[];

  tripDataSource: MatTableDataSource<Trip>;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private router: Router, private driverService: DriverService, private adminService: AdminService,
              private dataService: DataService, private storeService: StoreService) {
  }

  ngOnInit(): void {}

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
