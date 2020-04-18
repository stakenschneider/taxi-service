import {AbstractEntity} from './response/abstract.entity.model';
import {Client} from './actor/client.model';
import {Address} from './info/address.model';
import {Driver} from './actor/driver.model';

export interface Trip extends AbstractEntity {
  client: Client;
  driver: Driver;
  status: string;
  startAddress: Address;
  finishAddress: Address;
  dateOfCreation: string;
  dateOfCompletion: string;
  paymentMethod: string;
  rating: number;
  tripRate: string;
  price: number;
}
