import { Component, OnInit } from '@angular/core';
import Inputmask from 'inputmask';
import {Client} from '../../models/client.model';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {Person} from '../../models/person.model';

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
    const saltRounds = 10;

    if (this.password !== this.passwordRepeat) {
      alert('passwords don\'t match');
    } else {

      this.authService.signUp(this.email, this.password,  this.firstName, this.lastName).subscribe(
        data => {
          // tslint:disable-next-line:only-arrow-functions
          // bcrypt.hash(this.password, saltRounds, function(err, hash) {
          //   // Store hash in your password DB.
          // });
           this.person = data as Client;
        }, error => console.error(error)
      );
      this.router.navigateByUrl('/sign-in');
    }
  }
}
