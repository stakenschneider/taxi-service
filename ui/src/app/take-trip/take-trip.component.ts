import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {Trip} from '../../models/trip.model';
import {Router} from '@angular/router';
import {DriverService} from '../../services/driver.service';
import {DataService} from '../../services/data.service';
import {StoreService} from '../../services/store.service';
import {MatDialog} from '@angular/material/dialog';
import {DialogComponent} from '../dialog/dialog.component';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

export interface DialogData {
  trip: Trip;
}

@Component({
  selector: 'app-take-trip',
  templateUrl: './take-trip.component.html',
  styleUrls: ['./take-trip.component.css']
})
export class TakeTripComponent implements OnInit {
  displayedColumns: string[] = ['id', 'price', 'dateOfCreation', 'client', 'startAddress','finishAddress'];
  dataSource: MatTableDataSource<Trip>;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  trip: Trip;
  trips: Trip[];

  constructor(private router: Router, private driverService: DriverService,
              private dataService: DataService, private storeService: StoreService, public dialog: MatDialog) {
    this.dataService = dataService;
    this.storeService = storeService;
    this.driverService = driverService;
  }

  ngOnInit(): void {
    if (this.storeService.getId()) {
      this.getFreeTrips();
    } else {
      this.router.navigateByUrl('/sign-in');
    }
  }

  parseDate(input) {
    const parts = input.match(/(\d+)/g);
    return new Date(parts[0], parts[1] - 1, parts[2]);
  }

  getFreeTrips() {
    this.driverService.getFreeTrips().subscribe(
      data => {
        if (data.message === null) {
          this.trips = data.body;
          // tslint:disable-next-line:max-line-length
          const month = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
          this.trips.forEach(trip => {trip.client.rating = +trip.client.rating.toFixed(1); });
          this.trips.forEach(trip => {
            // tslint:disable-next-line:max-line-length
            trip.dateOfCreation = this.parseDate(trip.dateOfCreation).getUTCDay() + ' ' + month[this.parseDate(trip.dateOfCreation).getUTCMonth()] + ' ' + this.parseDate(trip.dateOfCreation).getUTCFullYear();
          });
          const dataSource = new MatTableDataSource(this.trips);
          dataSource.sort = this.sort;
          this.dataSource = dataSource;
        } else {
          // alert(data.message);
        }
      },
      error => {
        alert(error);
      });
  }

  endTrip(id: number) {
    this.driverService.endTrip(4.3, id).subscribe(
      data => {
        if (data.message === null) {
          // alert(data.message);
        } else {
          // alert(data.message);
        }
      },
      error => {
        alert(error);
      });
    this.getFreeTrips();
  }

  reserveTrip(trip: Trip) {

    const dialogRef = this.dialog.open(DialogComponent, {
      data: {trip},
      disableClose: true
    });


    dialogRef.afterClosed().subscribe(
      result => {
        this.endTrip(trip.id);
      });
  }
}
