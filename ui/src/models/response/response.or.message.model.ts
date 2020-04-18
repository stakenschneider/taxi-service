import {ApiResult} from './api.result.model';

export class ResponseOrMessage <T> extends ApiResult {
  body: T;
}
