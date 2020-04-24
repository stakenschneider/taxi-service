import {Component, OnInit} from '@angular/core';
import {Trip} from '../../../models/trip.model';
import {Router} from '@angular/router';
import {DriverService} from '../../../services/driver.service';
import {DataService} from '../../../services/data.service';
import {StoreService} from '../../../services/store.service';
import {MatDialog} from '@angular/material/dialog';
import {DialogComponent} from '../../components/dialog/dialog.component';
import {openSnackBar} from '../../open.snack.bar';
import {MatSnackBar} from '@angular/material/snack-bar';
import {UiGridData} from '../../../models/table/ui.grid.data.model';

export interface DialogData {
  trip: Trip;
}

@Component({
  selector: 'app-take-trip',
  templateUrl: './take-trip.component.html',
  styleUrls: ['./take-trip.component.css']
})
export class TakeTripComponent implements OnInit {
  getDataForTrips: any = {};
  parametersForTrips: Map<string, any> = new Map<string, any>();
  uiGridData: UiGridData = new UiGridData();

  // tslint:disable-next-line:variable-name
  constructor(private _snackBar: MatSnackBar, private router: Router, private driverService: DriverService,
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
          console.log( data.message);
        }
      },
      error => {
        alert(error);
      }
    );
  }

  reserveTrip(tripId: number) {
    this.dataService.getTripById(tripId).subscribe(data => {
      if (data.message) {
        console.log( data.message);
      }

      const trip: Trip = data.body;
      const dialogRef = this.dialog.open(DialogComponent, {
        data: {trip},
        disableClose: true
      });

      dialogRef.afterClosed().subscribe(
        result => {
          openSnackBar(this._snackBar, result, 5);

        });
    }, error => {
      alert(error);
    });
  }

  onCellClick(e: any) {
    this.reserveTrip(e.row[0]);
  }
}
