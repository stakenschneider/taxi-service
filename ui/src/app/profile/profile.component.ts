import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {Trip} from '../../models/trip.model';
import {Driver} from '../../models/actor/driver.model';
import {StoreService} from '../../services/store.service';
import {DriverService} from '../../services/driver.service';
import {DialogComponent} from '../dialog/dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {DialogHistoryTripComponent} from '../dialog-history-trip/dialog-history-trip.component';

export interface DataHistoryTrip {
  trip: Trip;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  public firstName: string;
  public lastName: string;
  public username: string;
  public email: string;
  public rating: string;
  public phoneNumber: string;
  public personType: string;
  public showTrips: boolean;

  // public headers = ['id', 'price', 'paymentMethod', 'tripRate', 'rating', 'status',
  //   'driver', 'car', 'start address', 'finish address'];

  public headers = ['start address', 'finish address'];

  public tripsArray: Array<Trip>;
  public driver: Driver;
  isClient = false;
  isDriver = false;
  isAdmin = false;
  showPassportForm = false;
  showRegisterCarForm = false;
  passport: { series: string, number: string } = {series: '', number: ''};
  ifPersonExist = false;
  public carColors: Array<string>;
  public carModels: Array<string>;
  public colorTitle: string;
  public modelTitle: string;
  public carNumber: string;
  tripsHistoryTitle = 'Show trips';

  constructor(private router: Router, private driverService: DriverService,
              private dataService: DataService, private storeService: StoreService, public dialog: MatDialog) {
    this.dataService = dataService;
    this.showTrips = false;
    this.storeService = storeService;
    this.driverService = driverService;
  }

  ngOnInit(): void {
    if (this.storeService.getIsAuth()) {
      this.dataService.getPersonById(this.storeService.getId(), this.storeService.getPersonType()).subscribe(
        data => {
          if (data.message === null) {
            this.firstName = data.body.firstName;
            this.lastName = data.body.lastName;
            this.email = data.body.credentials.email;
            this.username = data.body.credentials.username;
            // this.phoneNumber = data.body.phoneNumber;
            this.personType = data.body.personType;

            switch (this.personType) {
              case 'CLIENT':
                this.isClient = true;
                break;
              case 'DRIVER':
                this.isDriver = true;
                break;
              case 'ADMIN':
                this.isAdmin = true;
                break;
            }

            this.ifPersonExist = true;
          } else {
            this.ifPersonExist = false;
            alert(data.message);
          }
        },
        error => {
          alert(error);
        });
    } else {
      this.router.navigateByUrl('/sign-in');
    }

  }

  showHistoryOfTrips() {
    if (this.showTrips) {
      this.showTrips = false;
    } else {
      this.dataService.getHistoryOfTips(this.storeService.getId()).subscribe(
        data => {
          if (data.message === null) {
            this.tripsHistoryTitle = 'Hide history of trips';
            this.tripsArray = data.body;
            this.showTrips = true;
          } else {
            alert(data.message);
          }
        },
        error => {
          alert(error);
        });
    }
  }

  openRegisterCarForm() {
    this.dataService.getCarColorList().subscribe(
      data => {
        this.carColors = data;
      });

    this.dataService.getCarModelList().subscribe(
      data => {
        this.carModels = data;
      });
    this.showRegisterCarForm = true;
  }

  setCar() {
    if (this.colorTitle && this.modelTitle && this.carNumber) {
      this.driverService.setCar(this.storeService.getId(), this.carNumber, this.modelTitle, this.colorTitle).subscribe(
        data => {
          alert(data.message);
          this.showRegisterCarForm = false;
        }, error => alert(error)
      );
    } else {
      alert('All fields required');
    }
  }

  openPassportForm() {
    this.showPassportForm = true;
  }

  setPassport() {
    if (this.passport.series && this.passport.number) {
      this.driverService.setPassport(this.storeService.getId(), this.passport.series, this.passport.number).subscribe(
        data => {
          alert(data.message);
          this.showPassportForm = false;
        }, error => alert(error)
      );
    } else {
      alert('All fields required');
    }
  }

  clickOnTableRow(trip: Trip) {

    const dialogRef = this.dialog.open(DialogHistoryTripComponent, {
      data: {trip}});

    dialogRef.afterClosed().subscribe(
      result => {
      });
  }
}


