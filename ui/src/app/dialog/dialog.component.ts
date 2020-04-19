import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {DriverService} from '../../services/driver.service';
import {DataService} from '../../services/data.service';
import {StoreService} from '../../services/store.service';
import {DialogData} from '../pages/take-trip/take-trip.component';
import {openSnackBar} from '../open.snack.bar';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})

export class DialogComponent implements OnInit {
  // tslint:disable-next-line:variable-name
  constructor(private _snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<DialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData, private router: Router, private driverService: DriverService,
              private dataService: DataService, private storeService: StoreService) {
  }

  buttonLabel = 'Start trip';

  ngOnInit() {
    if (this.data.trip.status === 'START') {
      this.buttonLabel = 'End trip';
    }
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
        if (data.message !== null) {
          openSnackBar(this._snackBar, data.message, 5);
        }
      },
      error => {
        alert(error);
      });
  }

  endTrip(id: number) {
    // TODO think about how to create field grade
    this.driverService.endTrip(4.3, id).subscribe(
      data => {
        if (data.message !== null) {
          openSnackBar(this._snackBar, data.message, 5);
        }
      },
      error => {
        alert(error);
      });
  }
}
