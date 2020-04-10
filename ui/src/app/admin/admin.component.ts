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
  public tripsArray: Trip[];
  public clientsArray: Client[];
  public driversArray: Driver[];
  tripsColumns: string[] = ['id', 'price', 'tripRate', 'paymentMethod', 'status', 'rating', 'dateOfCreation',
    'dateOfCompletion', 'client', 'driver', 'startAddress', 'finishAddress'];

  tripDataSource: MatTableDataSource<Trip>;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private router: Router, private driverService: DriverService, private adminService: AdminService,
              private dataService: DataService, private storeService: StoreService) {
  }

  ngOnInit(): void {
    this.showAllTrips();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.tripDataSource.filter = filterValue.trim().toLowerCase();
  }

  parseDate(input) {
    const parts = input.match(/(\d+)/g);
    return new Date(parts[0], parts[1] - 1, parts[2]);
  }

  showAllTrips() {
    this.adminService.getAllTrips().subscribe(
      data => {
        this.tripsArray = data.body;
        // tslint:disable-next-line:max-line-length
        const month = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
        this.tripsArray.forEach(trip => {
          trip.client.rating = +trip.client.rating.toFixed(1);
          if (trip.driver) { trip.driver.rating = +trip.driver.rating.toFixed(1); }
        });
        this.tripsArray.forEach(trip => {
          // tslint:disable-next-line:max-line-length
          trip.dateOfCreation = this.parseDate(trip.dateOfCreation).getUTCDate() + ' ' + month[this.parseDate(trip.dateOfCreation).getUTCMonth()] + ' ' + this.parseDate(trip.dateOfCreation).getUTCFullYear();
          // tslint:disable-next-line:max-line-length
          if (trip.dateOfCompletion) { trip.dateOfCompletion = this.parseDate(trip.dateOfCompletion).getUTCDate() + ' ' + month[this.parseDate(trip.dateOfCompletion).getUTCMonth()] + ' ' + this.parseDate(trip.dateOfCompletion).getUTCFullYear(); }

        });

        const dataSource = new MatTableDataSource(this.tripsArray);
        dataSource.sort = this.sort;
        this.tripDataSource = dataSource;
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
