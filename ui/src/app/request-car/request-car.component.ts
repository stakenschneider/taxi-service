import {Component, OnInit} from '@angular/core';
import {DataService} from '../../services/data.service';
import {ClientService} from '../../services/client.service';
import {Address} from '../../models/address.model';
import {Driver} from '../../models/actor/driver.model';
import {StoreService} from '../../services/store.service';

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

  constructor(private dataService: DataService, private clientService: ClientService, private storeService: StoreService) {
    this.dataService = dataService;
    this.storeService = storeService;
  }

  ngOnInit(): void {

    // TODO add method GET to backend and call it here
    // that method must show exist activ trip on this client
    // if client was activ trips must create a component with this context
    // TODO AND add to backend a verification which can check: 'have a person activ trip or no?'

    this.source.addEventListener('message', message => {
      this.driver = JSON.parse(message.data) as Driver;;
      this.statusFrameTitle = 'The driver is already coming to you!';
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

      this.clientService.requestCar(this.storeService.getId(), startAddress, finishAddress, this.paymentMethodsTitle, this.rateTitle).subscribe(
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
    this.clientService.denyTrip(2).subscribe(
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
