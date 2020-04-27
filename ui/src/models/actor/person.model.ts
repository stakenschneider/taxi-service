import {AbstractEntity} from '../response/abstract.entity.model';
import {Credentials} from '../info/credentials.model';
import {Passport} from '../info/passport.model';

export interface Person extends AbstractEntity {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  credentials: Credentials;
  passport: Passport;
  deleted: boolean;
}
