import {Injectable} from '@angular/core';

@Injectable()
export class StoreService {

  private id: number;

  constructor() {}

  getId() {
    return this.id;
  }

  setId(id: number) {
    this.id = id;
  }
}
