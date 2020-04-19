import {Component, Inject, OnInit} from '@angular/core';

import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
// import {DialogData} from '../pages/take-trip/take-trip.component';
import {Router} from '@angular/router';
import {DriverService} from '../../services/driver.service';
import {DataService} from '../../services/data.service';
import {StoreService} from '../../services/store.service';
import {Trip} from '../../models/trip.model';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})

export class DialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: number, private router: Router, private driverService: DriverService,
    private dataService: DataService, private storeService: StoreService) {
  }

  buttonLabel = 'Start trip';

  ngOnInit() {
    //  TODO get trip for trip id
    // if (this.data.trip.status === 'START') {
    //   this.buttonLabel = 'End trip';
    // }
  }

  closeModal() {
    this.dialogRef.close();
  }

  startTrip(tripId: number) {
    if (this.buttonLabel === 'End trip') {
      this.endTrip(tripId);
      this.closeModal();
    }
    this.buttonLabel = 'End trip';

    // TODO if trip was deny show a message or reload data
    this.driverService.reserveTrip(this.storeService.getId(), tripId).subscribe(
      data => {
        if (data.message === null) {
          console.log(data.message);
        } else {
          console.log(data.message);
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
  }
}
