import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  public flag: boolean;
  public flag2: boolean;
  public email: string;


  constructor() {
    this.flag = false;
    this.flag2 = true;
  }

  ngOnInit(): void {
  }

  sendMail() {
    this.flag = true;
    this.flag2 = false;
  }
}
