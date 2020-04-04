import {Component, Inject, OnInit} from '@angular/core';
import {Trip} from '../../models/trip.model';
import {Router} from '@angular/router';
import {DriverService} from '../../services/driver.service';
import {DataService} from '../../services/data.service';
import {StoreService} from '../../services/store.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-take-trip',
  templateUrl: './take-trip.component.html',
  styleUrls: ['./take-trip.component.css']
})
export class TakeTripComponent implements OnInit {

  headers = ['', 'id', 'price', 'pm', 'rate', 'client rating', 'phone number', 'start address', 'nd address', 'date'];
  trips: Array<Trip>;
  isReserve = false;

  constructor(private router: Router, private driverService: DriverService,
              private dataService: DataService, private storeService: StoreService) {
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

  getFreeTrips() {
    this.driverService.getFreeTrips().subscribe(
      data => {
        if (data.message === null) {
          this.trips = data.body;
        } else {
          alert(data.message);
        }
      },
      error => {
        alert(error);
      });
  }

  reserveTrip(id: number) {
    this.driverService.reserveTrip(this.storeService.getId(), id).subscribe(
      data => {
        if (data.message === null) {
          alert(data.message);
        } else {
          alert(data.message);
        }
      },
      error => {
        alert(error);
      });
    // TODO fix reserve into end in all rows
    this.isReserve = true;
  }

  endTrip(id: number) {
    this.driverService.endTrip(4.3, id).subscribe(
      data => {
        if (data.message === null) {
          alert(data.message);
        } else {
          alert(data.message);
        }
      },
      error => {
        alert(error);
      });
    this.isReserve = false;
    this.getFreeTrips();
  }
}

// @Component({
//   selector: 'app-dialog',
//   templateUrl: 'dialog.html',
// })
// export class Dialog {
//
//   constructor(
//     public dialogRef: MatDialogRef<Dialog>,
//     @Inject(MAT_DIALOG_DATA) public data: DialogData) {
//
//
//   }
//
//   onNoClick(): void {
//     this.dialogRef.close();
//   }
//
// }
