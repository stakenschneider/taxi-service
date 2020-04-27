import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../../../services/data.service';
import {Driver} from '../../../models/actor/driver.model';
import {StoreService} from '../../../services/store.service';
import {DriverService} from '../../../services/driver.service';
import {AdminService} from '../../../services/admin.service';
import {UiGridData} from '../../../models/table/ui.grid.data.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  getDataForTrips: any = {};
  parametersForTrips: Map<string, any> = new Map<string, any>();
  uiGridData: UiGridData = new UiGridData();
  public person: any;
  public driver: Driver;

  public carColors: Array<string>;
  public carModels: Array<string>;
  // TODO this is must be in formGrop
  public carNumber: string;
  public colorTitle: string;
  public modelTitle: string;

  public passportSeries: string;
  public passportNumber: string;

  public showTrips = false;
  public panelOpenState = false;

  public personType: string;

  public tripsHistoryTitle = 'Show trips';

  constructor(private router: Router, private driverService: DriverService, private adminService: AdminService,
              private dataService: DataService, private storeService: StoreService) {
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

    // TODO load when open or when person is DRIVER
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
    this.personType = this.storeService.getPersonType();
    this.dataService.getPersonById(this.storeService.getId(), this.storeService.getPersonType()).subscribe(
      data => {
        if (data.message === null) {
          this.person = data.body;
          if (this.person.rating) {
            this.person.rating = +this.person.rating.toFixed(1);
          }
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
      if (this.storeService.getPersonType() === 'CLIENT') {
        this.showHistoryOfTripsForClient();
      } else if (this.storeService.getPersonType() === 'DRIVER') {
        this.showHistoryOfTripsForDriver();
      }
      this.showTrips = true;
      this.tripsHistoryTitle = 'Hide history of trips';
    }
  }

  showHistoryOfTripsForClient() {
    this.getDataForTrips.dataName = 'tripTable';
    this.parametersForTrips.set('for', 'CLIENT');
    this.parametersForTrips.set('personId', this.storeService.getId());
    this.getDataForTrips.parameters = this.parametersForTrips;
  }

  showHistoryOfTripsForDriver() {
    this.getDataForTrips.dataName = 'tripTable';
    this.parametersForTrips.set('for', 'DRIVER');
    this.parametersForTrips.set('part', 'history');
    this.parametersForTrips.set('personId', this.storeService.getId());
    this.getDataForTrips.parameters = this.parametersForTrips;
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

  generate() {
    this.adminService.generate(20).subscribe(
      data => {
        alert(data.message);
      },
      error => {
        alert(error);
      }
    );
  }

  // clickOnTableRow(trip: Trip) {
  //
  //   const dialogRef = this.dialog.open(DialogHistoryTripComponent, {
  //     data: {trip}
  //   });
  //
  //   dialogRef.afterClosed().subscribe(
  //     result => {
  //       console.log(result.toString());
  //     });
  // }

  // TODO IMPLEMENT OR DELETE
  changeCar() {}
}


