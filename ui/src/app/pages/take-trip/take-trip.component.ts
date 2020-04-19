import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {Trip} from '../../../models/trip.model';
import {Router} from '@angular/router';
import {DriverService} from '../../../services/driver.service';
import {DataService} from '../../../services/data.service';
import {StoreService} from '../../../services/store.service';
import {MatDialog} from '@angular/material/dialog';
import {DialogComponent} from '../../dialog/dialog.component';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SnackBarComponent} from '../../snack-bar/snack-bar.component';

// export interface DialogData {
//   tripId: number;
// }

@Component({
  selector: 'app-take-trip',
  templateUrl: './take-trip.component.html',
  styleUrls: ['./take-trip.component.css']
})
export class TakeTripComponent implements OnInit {
  getDataForTrips: any = {};
  parametersForTrips: Map<string, any> = new Map<string, any>();

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
      this.getDataForTrips.dataName = 'tripTable';
      this.parametersForTrips.set('for', 'DRIVER');
      this.parametersForTrips.set('part', 'free');
      this.parametersForTrips.set('personId', this.storeService.getId());
      this.getDataForTrips.parameters = this.parametersForTrips;
    } else {
      this.router.navigateByUrl('/sign-in');
    }
  }

  ifExistTripWithStatusStart() {
    this.driverService.getCurrentTrip(this.storeService.getId()).subscribe(
      data => {
        if (data.message === null) {
          this.reserveTrip(data.body.id);
        } else {
          console.log(data.message);
        }
      },
      error => {
        alert(error);
      }
    );
  }

  // TODO reserve must work
  reserveTrip(tripId: number) {
    const dialogRef = this.dialog.open(DialogComponent, {
      data: {tripId},
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(
      result => {
        console.log(result);
      });
  }

  onCellClick(e: any) {
    this.reserveTrip(e.row[0]);
  }

  openSnackBar(message: string) {
    this._snackBar.openFromComponent(SnackBarComponent, {
      duration: this.durationInSecondsForSnackBar * 1000,
      data: message
    });
  }
}
