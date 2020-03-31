import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit, OnDestroy {
  public isAuthenticated: boolean;
  private authChangedSubscriber: Subscription;
  constructor(private router: Router, private storeService: StoreService) {
  }

  ngOnInit(): void {
    this.authChangedSubscriber = this.storeService.authChanged.asObservable().subscribe(x => {
      this.isAuthenticated = x;
    });
  }

  ngOnDestroy(): void {
    this.authChangedSubscriber.unsubscribe();
  }

  logOut() {
    this.storeService.setIsAuth(false);
    return this.router.navigateByUrl('/home');
  }

  home() {
    return this.router.navigateByUrl('/home');
  }

  notFound() {
    return this.router.navigateByUrl('/not-found');
  }

  profile() {
    return this.router.navigateByUrl('/profile');
  }

  requestCar() {
    return this.router.navigateByUrl('/request-car');
  }

  settings() {
    return this.router.navigateByUrl('/settings');
  }

}
