import {AbstractEntity} from './abstract.entity.model';

export interface Passport extends AbstractEntity {
  series: string;
  number: string;
}
