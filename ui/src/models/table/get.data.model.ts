export class GetData {
  // tslint:disable-next-line:variable-name
  private _dataName: string;
  // tslint:disable-next-line:variable-name
  private _parameters: Map<string, any>;

  get parameters(): Map<string, any> {
    return this._parameters;
  }

  set parameters(value: Map<string, any>) {
    this._parameters = value;
  }

  get dataName(): string {
    return this._dataName;
  }

  set dataName(value: string) {
    this._dataName = value;
  }
}
