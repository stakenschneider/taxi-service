import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {Trip} from '../../../models/trip.model';
import {Router} from '@angular/router';
import {DriverService} from '../../../services/driver.service';
import {DataService} from '../../../services/data.service';
import {StoreService} from '../../../services/store.service';
import {MatDialog} from '@angular/material/dialog';
import {DialogComponent} from '../../dialog/dialog.component';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SnackBarComponent} from '../../snack-bar/snack-bar.component';

export interface DialogData {
  trip: Trip;
}

@Component({
  selector: 'app-take-trip',
  templateUrl: './take-trip.component.html',
  styleUrls: ['./take-trip.component.css']
})
export class TakeTripComponent implements OnInit {
  displayedColumns: string[] = ['id', 'price', 'dateOfCreation', 'client', 'startAddress', 'finishAddress'];
  dataSource: MatTableDataSource<Trip>;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  trip: Trip;
  trips: Trip[];
  public durationInSecondsForSnackBar = 5;

  // tslint:disable-next-line:variable-name
  constructor(private _snackBar: MatSnackBar,
              private router: Router, private driverService: DriverService,
              private dataService: DataService, private storeService: StoreService, public dialog: MatDialog) {
    this.dataService = dataService;
    this.storeService = storeService;
    this.driverService = driverService;
  }

  ngOnInit(): void {
    this.ifExistTripWithStatusStart();

    if (this.storeService.getId()) {
      this.getFreeTrips();
    } else {
      this.router.navigateByUrl('/sign-in');
    }
  }

  ifExistTripWithStatusStart() {
    this.driverService.getCurrentTrip(this.storeService.getId()).subscribe(
      data => {
        if (data.message === null) {
          this.reserveTrip(data.body);
        } else {
          console.log(data.message);
        }
      },
      error => {
        alert(error);
      }
    );
  }

  getFreeTrips() {
    this.driverService.getFreeTrips(this.storeService.getId()).subscribe(
      data => {
        if (data.message === null) {
          this.trips = data.body;
          const month = ['January', 'February', 'March', 'April',
            'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
          this.trips.forEach(trip => {
            trip.client.rating = +trip.client.rating.toFixed(1);
          });
          const dataSource = new MatTableDataSource(this.trips);
          dataSource.sort = this.sort;
          this.dataSource = dataSource;
        } else {
          this.openSnackBar(data.message);
        }
      },
      error => {
        alert(error);
      });
  }

  reserveTrip(trip: Trip) {
    const dialogRef = this.dialog.open(DialogComponent, {
      data: {trip},
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(
      result => {
        console.log(result.toString());
        this.getFreeTrips();
      });
  }

  openSnackBar(message: string) {
    this._snackBar.openFromComponent(SnackBarComponent, {
      duration: this.durationInSecondsForSnackBar * 1000,
      data: message
    });
  }
}
