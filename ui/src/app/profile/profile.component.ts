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
import {AdminService} from '../../services/admin.service';

export interface DataHistoryTrip {
  trip: Trip;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  public person: any;
  public driver: Driver;

  public tripsArray: Array<Trip>;
  public carColors: Array<string>;
  public carModels: Array<string>;

  public carNumber: string;
  public colorTitle: string;
  public modelTitle: string;

  public passportSeries: string;
  public passportNumber: string;

  public showTrips = false;
  public panelOpenState = false;

  public tripsHistoryTitle = 'Show trips';

  constructor(private router: Router, private driverService: DriverService, private adminService: AdminService,
              private dataService: DataService, private storeService: StoreService, public dialog: MatDialog) {
    this.dataService = dataService;
    this.adminService = adminService;
    this.storeService = storeService;
    this.driverService = driverService;
  }

  ngOnInit(): void {
    if (this.storeService.getIsAuth()) {
      this.getPerson();
    } else {
      this.router.navigateByUrl('/sign-in');
    }

    this.dataService.getCarColorList().subscribe(
      data => {
        this.carColors = data;
      });

    this.dataService.getCarModelList().subscribe(
      data => {
        this.carModels = data;
      });
  }

  getPerson() {
    this.dataService.getPersonById(this.storeService.getId(), this.storeService.getPersonType()).subscribe(
      data => {
        if (data.message === null) {
          this.person = data.body;
          this.person.rating = +this.person.rating.toFixed(1);
        } else {
          alert(data.message);
        }
      },
      error => {
        alert(error);
      });
  }

  showHistoryOfTrips() {
    if (this.showTrips) {
      this.showTrips = !this.showTrips;
      this.tripsHistoryTitle = 'Show trips';
    } else {
      if (this.person.personType === 'CLIENT') {
        this.showHistoryOfTripsForClient();
      } else if (this.person.personType === 'DRIVER') {
        this.showHistoryOfTripsForDriver();
      }
      this.showTrips = true;
      this.tripsHistoryTitle = 'Hide history of trips';
    }
  }

  formatDate() {
    const month =
      ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    this.tripsArray.forEach(trip => {
      trip.dateOfCreation = this.parseDate(trip.dateOfCreation).getDate() +
        ' ' + month[this.parseDate(trip.dateOfCreation).getUTCMonth()] + ' ' + this.parseDate(trip.dateOfCreation).getUTCFullYear();
    });
  }

  showHistoryOfTripsForClient() {
    this.dataService.getHistoryOfTips(this.storeService.getId()).subscribe(
      data => {
        if (data.message === null) {
          this.tripsArray = data.body;
          this.formatDate();
        } else {
          alert(data.message);
        }
      },
      error => {
        alert(error);
      });
  }

  showHistoryOfTripsForDriver() {
    this.driverService.getHistory(this.storeService.getId()).subscribe(
      data => {
        if (data.message === null) {
          this.tripsArray = data.body;
          this.formatDate();
        } else {
          alert(data.message);
        }
      },
      error => {
        alert(error);
      });
  }


  parseDate(input) {
    const parts = input.match(/(\d+)/g);
    return new Date(parts[0], parts[1] - 1, parts[2]);
  }

  setCar() {
    if (this.colorTitle && this.modelTitle && this.carNumber) {
      this.driverService.setCar(this.storeService.getId(), this.carNumber, this.modelTitle, this.colorTitle).subscribe(
        data => {
          alert(data.message);
        }, error => alert(error)
      );
      //  TODO after this must be disabled inputs
    } else {
      alert('All fields required');
    }
  }

  setPassport() {
    if (this.passportSeries && this.passportNumber) {
      this.driverService.setPassport(this.storeService.getId(), this.passportSeries, this.passportNumber).subscribe(
        data => {
          alert(data.message);
        }, error => alert(error)
      );
    //  TODO after this must be disabled inputs
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

  changeCar() {

  }
}


