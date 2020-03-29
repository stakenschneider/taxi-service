import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  public flag: boolean;
  public email = '';
  @ViewChild('emailInput') emailInput: ElementRef;

  constructor() {
    this.flag = false;
  }

  ngOnInit(): void {
  }

  sendMail() {
    this.email = this.emailInput.nativeElement.value;
    this.flag = true;
  }
}
