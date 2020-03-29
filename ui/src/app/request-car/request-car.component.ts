import {Component, OnInit} from '@angular/core';
import {DataService} from '../../services/data.service';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-request-car',
  templateUrl: './request-car.component.html',
  styleUrls: ['./request-car.component.css']
})
export class RequestCarComponent implements OnInit {
  public fromCity: string;
  public fromStreet: string;
  public fromNumber: string;

  public toCity: string;
  public toStreet: string;
  public toNumber: string;

  public rate: Array<string>;
  paymentMethods = ['lol', 'lal'];


  constructor(private dataService: DataService) {
    this.dataService = dataService;

  }

  ngOnInit(): void {
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
  }
}
