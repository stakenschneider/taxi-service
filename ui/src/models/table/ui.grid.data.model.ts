export class UiGridData {
  constructor() {
    this._showFilter = true;
    this._showPaginator = true;
    this._pageSizeOptions = [5, 10, 20];
    this._cellsIsEditable = false;
  }

  get showPaginator(): boolean {
    return this._showPaginator;
  }

  set showPaginator(value: boolean) {
    this._showPaginator = value;
  }

  get cellsIsEditable(): boolean {
    return this._cellsIsEditable;
  }

  set cellsIsEditable(value: boolean) {
    this._cellsIsEditable = value;
  }

  get showFilter(): boolean {
    return this._showFilter;
  }

  set showFilter(value: boolean) {
    this._showFilter = value;
  }

  get pageSizeOptions(): number[] {
    return this._pageSizeOptions;
  }

  set pageSizeOptions(value: number[]) {
    this._pageSizeOptions = value;
  }

  // tslint:disable-next-line:variable-name
  private _pageSizeOptions: number[];
  // tslint:disable-next-line:variable-name
  private _showFilter: boolean;
  // tslint:disable-next-line:variable-name
  private _showPaginator: boolean;
  // tslint:disable-next-line:variable-name
  private _cellsIsEditable: boolean;
}
