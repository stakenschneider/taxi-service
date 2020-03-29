import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
// import {StoreService} from '../../services/store.service';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {
  public isAuthenticated: boolean;
  constructor(private router: Router) { }

  ngOnInit(): void {
    // todo true - виден logout, profile, settings
    // false - виден sign-in
    // this.isAuthenticated = this.storeService !== null;
this.isAuthenticated = true;
  }

  logOut() {
    // this.storeService.setId(null);
    this.isAuthenticated = false;
    return this.router.navigateByUrl('/home');
  }

}
