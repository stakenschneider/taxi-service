import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  public flag: boolean;
  public emailErrorFlag: boolean;
  public email = '';
  @ViewChild('emailInput') emailInput: ElementRef;


  constructor(private router: Router) {
    this.flag = true;
  }

  ngOnInit(): void {
  }

  sendMail() {
    this.flag = this.emailInput ? this.flag : false;

    this.email = this.emailInput.nativeElement.value;
    if (this.isEmail(this.email)) {
      this.flag = false;
    } else {
      this.emailErrorFlag = true;
    }
  }


  signIn() {
    return this.router.navigateByUrl('/sign-in');
  }

  isEmail(search: string): boolean {
    // tslint:disable-next-line:max-line-length
    return new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/).test(search);
  }
}
