import {Person} from './person.model';
import {Passport} from '../passport.model';

export interface Client extends Person {
  passport: Passport;
}
