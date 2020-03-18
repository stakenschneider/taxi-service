import {Component, OnInit} from '@angular/core';
import {Person} from '../../models/person.model';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  public email: string;
  public password: string;
  public person: Person;

  constructor(private router: Router,
              private authService: AuthService) {
    this.authService = authService;
  }

  ngOnInit(): void {
  }

  signIn() {
    if (this.email.length === 0 || this.password.length === 0) {
      alert('You must fill all fields');
    }

    // bcrypt.compare(password, hash, function(err, result) {
    //   // result == true
    // });

    this.authService.signIn(this.email, this.password).subscribe(
      data => {
        if (!data.message.empty) {
          alert(data.message);
        } else {
          return this.router.navigateByUrl('/home');
        }
      });
  }

  ngOnDestroy() {
    // this.authService.signIn().unsubscribe();
  }

  createAccount() {
    return this.router.navigateByUrl('/sign-up');
  }
}
