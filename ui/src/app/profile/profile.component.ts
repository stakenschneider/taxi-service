import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {DataService} from '../../services/data.service';
import {Trip} from '../../models/trip.model';

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
  public headers = ['id', 'driver', 'status', 'rating', 'tripRate', 'price', 'paymentMethod', 'dateOfCreation', 'dateOfCompletion'];
  public tripsArray: Array<Trip>;

  constructor(private router: Router,
              private dataService: DataService) {
    this.dataService = dataService;
    this.showTrips = false;
  }

  ngOnInit(): void {
    //todo clientid
    this.dataService.getPersonById(1).subscribe(
      data => {
        if (data.message === null) {
          this.firstName = data.body.firstName;
          this.lastName = data.body.lastName;
          this.email = data.body.credentials.email;
          this.username = data.body.credentials.username;
          this.phoneNumber = data.body.phoneNumber;
          this.personType = data.body.personType;
        } else {
          alert(data.message);
        }
      },
      error => {
        alert(error);
      });
  }

  showHistoryOfTrips() {
    // todo clientid
    this.dataService.getHistoryOfTips(1).subscribe(
      data => {
        if (data.message === null) {
          this.tripsArray = data.body;
        } else {
          alert(data.message);
        }
      },
      error => {
        alert(error);
      });
    this.showTrips = true;
  }
}


