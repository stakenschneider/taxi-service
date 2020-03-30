import {AbstractEntity} from './abstract.entity.model';

export interface Car extends AbstractEntity {
  carRate: string;
  number: string;
  model: string;
  color: string;
}
