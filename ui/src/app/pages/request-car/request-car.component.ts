import {Component, OnInit} from '@angular/core';
import {DataService} from '../../../services/data.service';
import {ClientService} from '../../../services/client.service';
import {Address} from '../../../models/info/address.model';
import {StoreService} from '../../../services/store.service';
import {Router} from '@angular/router';
import {Trip} from '../../../models/trip.model';
import {openSnackBar} from '../../open.snack.bar';
import {MatSnackBar} from '@angular/material/snack-bar';

declare let EventSource: any;

@Component({
  selector: 'app-request-car',
  templateUrl: './request-car.component.html',
  styleUrls: ['./request-car.component.css']
})
export class RequestCarComponent implements OnInit {
  // TODO переделать в форму
  public fromCity: string;
  public fromStreet: string;
  public fromNumber: number;

  public toCity: string;
  public toStreet: string;
  public toNumber: number;

  public rate: Array<string>;
  public paymentMethods: Array<string>;
  public rateTitle: string;
  public paymentMethodsTitle: string;
  source = new EventSource('http://localhost:8080/api/stream');
  // true - request car
  // false - deny trip
  public bttnRequestOrDeny = true;
  public endFrameShow = false;
  public statusFrameShow = false;

  public disabled = false;
  public trip: Trip;

  firstFrameTitle = 'REQUEST MAP FORM';
  statusFrameTitle: string;
  endFrameTitle: string;
  starRating: any;
  contentStatusFrame = false;

  constructor(private _snackBar: MatSnackBar, private router: Router, private dataService: DataService,
              private clientService: ClientService, private storeService: StoreService) {
    this.dataService = dataService;
    this.storeService = storeService;
  }

  ngOnInit(): void {
    if (this.storeService.getId()) {
      this.getActiveTrips();
      this.addEventListener();
      this.getRate();
      this.getPM();
    } else {
      this.router.navigateByUrl('/sign-in');
    }
  }

  getRate() {
    this.dataService.getRate().subscribe(
      data => {
        this.rate = data;
      });
  }

  getPM() {
    this.dataService.getPaymentMethod().subscribe(
      data => {
        this.paymentMethods = data as string[];
      }, error => {
        alert(error);
      });
  }

  addEventListener() {
    this.source.addEventListener('message', message => {
      if (this.trip) {
        this.endFrameTitle = 'Trip is over';
        this.endFrameShow = true;
        this.disabled = true;
      }

      this.trip = JSON.parse(message.data) as Trip;
      this.statusFrameTitle = 'The driver is already coming to you!';
      this.contentStatusFrame = true;
    });
  }

  getActiveTrips() {
    this.clientService.getActiveTrip(this.storeService.getId()).subscribe(
      data => {
        if (data.message === null) {
          this.toCity = data.body.finishAddress.city;
          this.toNumber = data.body.finishAddress.numberHouse;
          this.toStreet = data.body.finishAddress.street;

          this.fromCity = data.body.startAddress.city;
          this.fromStreet = data.body.startAddress.street;
          this.fromNumber = data.body.startAddress.numberHouse;

          this.statusFrameShow = true;
          this.bttnRequestOrDeny = false;
          this.disabled = true;

          this.rateTitle = data.body.tripRate;
          this.paymentMethodsTitle = data.body.paymentMethod;

          switch (data.body.status) {
            case 'CREATE':
              this.statusFrameTitle = 'The trip was created. Wait for a response.';
              break;
            case 'START':
              // TODO status maybe should be CREATE START DENY FINISH IN_PROCESS
              this.statusFrameTitle = 'Start trip status';
              this.trip = data.body;
              this.contentStatusFrame = true;
              break;
          }
        } else {
          console.log(data.message);
        }
      }, error => {
        alert(error);
      }
    );
  }

  // TODO высветить примерную стоимость поездки
  requestCar(): void {
    if (this.rateTitle && this.paymentMethodsTitle && this.fromNumber && this.fromStreet && this.fromCity &&
      this.toStreet && this.toNumber && this.toCity) {
      const startAddress = new Address(this.fromCity, this.fromStreet, this.fromNumber);
      const finishAddress = new Address(this.toCity, this.toStreet, this.toNumber);

      this.clientService.requestCar(this.storeService.getId(),
        startAddress, finishAddress, this.paymentMethodsTitle, this.rateTitle).subscribe(
        data => {
          this.disabled = true;
          this.statusFrameTitle = data.message;
          this.statusFrameShow = true;
          this.bttnRequestOrDeny = false;
        }, error => {
          alert(error);
        }
      );
    } else {
      openSnackBar(this._snackBar, 'All fields must be filled', 5);
    }
  }

  denyTrip() {
    this.clientService.denyTrip(this.storeService.getId()).subscribe(
      data => {
        this.statusFrameTitle = data.message;
        this.disabled = false;
        this.bttnRequestOrDeny = true;
        this.statusFrameShow = false;
      }, error => {
        alert(error);
      }
    );
  }

  setGrade() {
    this.clientService.setGrade(this.trip.id, this.starRating).subscribe(
      data => {
        this.endFrameTitle = data.message;
        this.disabled = false;
        this.bttnRequestOrDeny = true;
        this.statusFrameShow = false;
        this.endFrameShow = false;
        this.contentStatusFrame = false;
      }, error => {
        alert(error);
      }
    );
    openSnackBar(this._snackBar, 'Оценка учтена', 5);
  }

  setRate(value: string) {
    this.rateTitle = value;
  }

  setPM(value: string) {
    this.paymentMethodsTitle = value;
  }
}
