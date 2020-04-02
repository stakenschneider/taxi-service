import {EventEmitter, Injectable} from '@angular/core';

// TODO refactor this code when implement spring security
@Injectable()
export class StoreService {
  public id: number;
  public isAuth: boolean;
  public personType: string;
  public authChanged: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() {
    this.isAuth = false;
  }

  getPersonType() {
    return this.personType;
  }

  setPersonType(personType: string) {
    this.personType = personType;
  }

  getId() {
    return this.id;
  }

  setId(id: number) {
    this.id = id;
    this.setIsAuth(true);
  }

  getIsAuth() {
    return this.isAuth;
  }

  setIsAuth(bool: boolean) {
    this.isAuth = bool;
    this.authChanged.next(bool);
  }
}
