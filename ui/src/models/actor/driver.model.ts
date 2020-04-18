import {Person} from './person.model';
import {Car} from '../info/car.model';

export interface Driver extends Person {
  rating: number;
  car: Car;
  aviable: boolean;
}
