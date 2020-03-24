import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../../services/auth.service';
import * as bcrypt from 'bcryptjs';

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
              private authService: AuthService) {
    this.authService = authService;
  }

  ngOnInit(): void {
  }

  signIn() {
    if (!this.email || this.email.length === 0 || this.password.length === 0) {
      alert('You must fill all fields');
    }

    this.authService.signIn(this.email).subscribe(
      data => {
        if (data.message !== null) {
          this.flag = true;
        } else {
          const router = this.router;
          bcrypt.compare(this.password, data.body.credentials.password, function(err, result) {
            if (result) {
              return router.navigateByUrl('/home');
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
