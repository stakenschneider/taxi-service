import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../../../services/auth.service';
import * as bcrypt from 'bcryptjs';
import {StoreService} from '../../../../services/store.service';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  public email: string;
  public password: string;
  public flag: boolean;
  hide = true;

  constructor(public dialog: MatDialog,
              private router: Router, private authService: AuthService, private storeService: StoreService) {
  }

  openDialog() {
    this.dialog.open(DialogContentComponent);
  }

  ngOnInit(): void {
  }

  signIn() {
    this.flag = this.email || this.password ? this.flag : true;

    this.authService.
    signIn(this.email).subscribe(
      data => {
        if (data.message !== null) {
          this.flag = true;
        } else {
          bcrypt.compare(this.password, data.body.password, (err, result) => {
            if (result) {
              this.storeService.setPersonType(data.body.personType);
              this.storeService.setId(data.body.personId);
              return this.router.navigateByUrl('/profile');
            } else {
              this.flag = true;
            }
          });
        }
      }, error => alert(error));
  }

  createAccount() {
    return this.router.navigateByUrl('/sign-up');
  }
}

@Component({
  selector: 'app-dialog-content',
  templateUrl: 'dialog-content.html',
})
export class DialogContentComponent {}
