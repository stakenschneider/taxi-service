import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../../services/auth.service';
import * as bcrypt from 'bcryptjs';
import {StoreService} from '../../../services/store.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  public email: string;
  public password: string;
  public flag: boolean;

  constructor(private router: Router,
              private authService: AuthService, private storeService: StoreService) {
    this.authService = authService;
  }

  ngOnInit(): void {
  }

  signIn() {
    this.flag = this.email || this.password ? this.flag : true;

    this.authService.signIn(this.email).subscribe(
      data => {
        if (data.message !== null) {
          this.flag = true;
        } else {
          bcrypt.compare(this.password, data.body.credentials.password, (err, result) => {
            if (result) {
              this.storeService.setId(data.body.id);
              return this.router.navigateByUrl('/home');
            } else {
              this.flag = true;
            }
          });
        }
      });
  }

  // ngOnDestroy() {
  //   // this.authService.signIn().unsubscribe();
  // }

  createAccount() {
    return this.router.navigateByUrl('/sign-up');
  }
}
