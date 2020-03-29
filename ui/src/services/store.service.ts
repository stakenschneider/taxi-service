import {EventEmitter, Injectable} from '@angular/core';
import {Subject} from 'rxjs';

// TODO refactor this code when implement spring security
@Injectable()
export class StoreService {
  public id: number;
  public isAuth: boolean;
  public authChanged: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() {
    this.isAuth = false;
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
