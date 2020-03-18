import {AbstractEntity} from './abstract.entity.model';
import {Credentials} from './credentials.model';

export interface Person extends AbstractEntity {
  first_name: string;
  last_name: string;
  personType: string;
  credentials: Credentials;
}
