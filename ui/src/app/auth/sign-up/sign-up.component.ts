import { Component, OnInit } from '@angular/core';
import Inputmask from 'inputmask';
import {Client} from '../../../models/client.model';
import {Router} from '@angular/router';
import {AuthService} from '../../../services/auth.service';
import {Person} from '../../../models/person.model';
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
  protected person: Person;

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    var selector = document.getElementById("phone");

    var im = new Inputmask("+7(999)-999-99-99");
    im.mask(selector);
  }

  signUp() {
    if (this.password !== this.passwordRepeat) {
      alert('passwords don\'t match');
    } else {

      const salt = bcrypt.genSaltSync(10);
      this.password = bcrypt.hashSync(this.password, salt);

      this.authService.signUp(this.email, this.password,  this.firstName, this.lastName).subscribe(
        data => {
           this.person = data as Client;
        }, error => console.error(error)
      );
      this.router.navigateByUrl('/sign-in');
    }
  }


  signIn() {
    return this.router.navigateByUrl('/sign-in');
  }
}
