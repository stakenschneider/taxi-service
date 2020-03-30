import {AbstractEntity} from '../abstract.entity.model';
import {Credentials} from '../credentials.model';
import {Passport} from '../passport.model';

export interface Person extends AbstractEntity {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  credentials: Credentials;
  passport: Passport;
  personType: string;
}
