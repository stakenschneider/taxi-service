import {Person} from './person.model';

export interface Client extends Person {
  rating: number;
}
