import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {Trip} from '../../models/trip.model';
import {Driver} from '../../models/actor/driver.model';
import {StoreService} from '../../services/store.service';
import {DriverService} from '../../services/driver.service';
import {MatDialog} from '@angular/material/dialog';
import {DialogHistoryTripComponent} from '../dialog-history-trip/dialog-history-trip.component';
import {Person} from '../../models/actor/person.model';
import {Client} from '../../models/actor/client.model';

export interface DataHistoryTrip {
  trip: Trip;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  public person: Person;
  public driver: Driver;
  public tripsArray: Array<Trip>;

  public isClient = false;
  public isDriver = false;
  public isAdmin = false;
  public ifPersonExist = false;

  public showTrips: boolean;
  public showPassportForm = false;
  public showRegisterCarForm = false;

  public carColors: Array<string>;
  public carModels: Array<string>;
  public carNumber: string;

  public colorTitle: string;
  public modelTitle: string;
  public tripsHistoryTitle = 'Show trips';

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
            this.person = data.body;
            switch (this.person.personType) {
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
      this.showTrips = !this.showTrips;
      this.tripsHistoryTitle = 'Show trips';
    } else {
      this.dataService.getHistoryOfTips(this.storeService.getId()).subscribe(
        data => {
          if (data.message === null) {
            this.tripsHistoryTitle = 'Hide history of trips';
            this.tripsArray = data.body;
            // tslint:disable-next-line:max-line-length
            const month = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

            this.tripsArray.forEach(trip => {
              // tslint:disable-next-line:max-line-length
              trip.dateOfCreation = this.parseDate(trip.dateOfCreation).getUTCDay() + ' ' + month[this.parseDate(trip.dateOfCreation).getUTCMonth()] + ' ' + this.parseDate(trip.dateOfCreation).getUTCFullYear();
            });
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

  parseDate(input) {
    const parts = input.match(/(\d+)/g);
    // new Date(year, month [, date [, hours[, minutes[, seconds[, ms]]]]])
    return new Date(parts[0], parts[1] - 1, parts[2]); // months are 0-based
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
    this.showPassportForm = !this.showPassportForm;
  }

  setPassport() {
    if (this.person.passport.series && this.person.passport.number) {
      this.driverService.setPassport(this.storeService.getId(), this.person.passport.series, this.person.passport.number).subscribe(
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
      data: {trip}
    });

    dialogRef.afterClosed().subscribe(
      result => {
      });
  }
}


