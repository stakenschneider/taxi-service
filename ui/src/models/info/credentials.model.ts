import {AbstractEntity} from '../response/abstract.entity.model';

export class Credentials extends AbstractEntity {
  email: string;
  password: string;
  username: string;
}
