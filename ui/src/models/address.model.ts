import {AbstractEntity} from './abstract.entity.model';

export interface Address extends AbstractEntity {
  city: string;
  street: string;
  numberHouse: number;
}
