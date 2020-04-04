import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DataHistoryTrip} from '../profile/profile.component';

@Component({
  selector: 'app-dialog-history-trip',
  templateUrl: './dialog-history-trip.component.html',
  styleUrls: ['./dialog-history-trip.component.css']
})
export class DialogHistoryTripComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<DialogHistoryTripComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DataHistoryTrip) {}

  ngOnInit() {}

  closeModal() {
    this.dialogRef.close();
  }
}
