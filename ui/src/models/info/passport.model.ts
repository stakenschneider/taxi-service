import {AbstractEntity} from '../response/abstract.entity.model';

export interface Passport extends AbstractEntity {
  series: string;
  number: string;
}
