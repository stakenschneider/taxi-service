import {Component, Inject, OnInit} from '@angular/core';

import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DialogData} from '../take-trip/take-trip.component';
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
    @Inject(MAT_DIALOG_DATA) public data: DialogData, private router: Router, private driverService: DriverService,
    private dataService: DataService, private storeService: StoreService) {}

  // If the user clicks the cancel button a.k.a. the go back button, then\
  // just close the modal
  buttonLabel = 'Start trip';

  ngOnInit() {
  }

  closeModal() {
    this.dialogRef.close();
  }

  startTrip(trip: Trip) {
    if (this.buttonLabel === 'End trip') {
      this.closeModal();
    }

    this.buttonLabel = 'End trip';

    this.driverService.reserveTrip(this.storeService.getId(), trip.id).subscribe(
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
}
