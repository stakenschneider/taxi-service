import {AbstractEntity} from './abstract.entity.model';

export class Credentials extends AbstractEntity {
  email: string;
  password: string;
}
