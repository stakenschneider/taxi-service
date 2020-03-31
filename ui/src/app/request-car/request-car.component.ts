import {Component, OnInit} from '@angular/core';
import {DataService} from '../../services/data.service';
import {ClientService} from '../../services/client.service';
import {Address} from '../../models/address.model';
import {Driver} from '../../models/actor/driver.model';

declare let EventSource: any;

@Component({
  selector: 'app-request-car',
  templateUrl: './request-car.component.html',
  styleUrls: ['./request-car.component.css']
})
export class RequestCarComponent implements OnInit {
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
  public driver: Driver;

  statusFrameTitle: string;

  constructor(private dataService: DataService, private clientService: ClientService) {
    this.dataService = dataService;
  }

  ngOnInit(): void {
    this.source.addEventListener('message', message => {
      const n = JSON.parse(message.data) as Driver;
      this.driver = n;
      this.statusFrameTitle = 'The driver is already coming to you!';
      // '\n Last name:' + n.lastName + '\n phone number' +
      //   n.phoneNumber + '\nCar: ' + n.car.number + n.car.model + n.car.color;
    });

    this.dataService.getPaymentMethod().subscribe(
      data => {
        this.paymentMethods = data as string[];
      }, error => {
      });

    this.dataService.getRate().subscribe(
      data => {
        this.rate = data;
      });
  }

  requestCar(): void {
    if (this.rateTitle && this.paymentMethodsTitle && this.fromNumber && this.fromStreet && this.fromCity &&
      this.toStreet && this.toNumber && this.toCity) {
      const startAddress = new Address(this.fromCity, this.fromStreet, this.fromNumber);
      const finishAddress = new Address(this.toCity, this.toStreet, this.toNumber);

      this.clientService.requestCar(1, startAddress, finishAddress, this.paymentMethodsTitle, this.rateTitle).subscribe(
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
      alert('All fields must be filled');
    }
  }

  denyTrip() {
    this.clientService.denyTrip(8).subscribe(
      data => {
        this.statusFrameTitle = data.message;
        this.disabled = false;
        this.bttnRequestOrDeny = true;
      }, error => {
        alert(error);
      }
    );
  }
}
