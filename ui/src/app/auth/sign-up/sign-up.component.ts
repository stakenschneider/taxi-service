import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../../services/auth.service';
import * as bcrypt from 'bcryptjs';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  public email: string;
  public password: string;
  public passwordRepeat: string;
  public firstName: string;
  public lastName: string;
  public flag: boolean;
  checkBoxValue: boolean;
  personType: string;

  constructor(private router: Router, private authService: AuthService) {
    this.flag = false;
  }

  ngOnInit(): void {
    // var selector = document.getElementById('phone');
    //
    // var im = new Inputmask('+7(999)-999-99-99');
    // im.mask(selector);
  }

  signUp() {
    if (this.password !== this.passwordRepeat) {
      //todo пароль не совпалает
      this.flag = true;
    } else {
      if (!this.isEmail(this.email)) {
        //todo эмейл не корректный
        this.flag = true;
      } else {
        if (this.password.length < 7) {
          //  todo пароль маленький
          this.flag = true;
        } else {
          const salt = bcrypt.genSaltSync(10);
          if (this.checkBoxValue) {
            this.personType = 'DRIVER';
          } else {
            this.personType = 'CLIENT';
          }
          this.authService.signUp(this.email, bcrypt.hashSync(this.password, salt),
            this.firstName, this.lastName, this.personType).subscribe(
            data => {
              if (data.body) {
                this.router.navigateByUrl('/sign-in');
              } else {
                alert(data.message);
              }
            }, error => alert(error)
          );
        }
      }
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
