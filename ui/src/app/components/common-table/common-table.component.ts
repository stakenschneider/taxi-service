import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {GetData} from '../../../models/table/get.data.model';
import {GridDataService} from '../../../services/grid.data.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {UiGridData} from '../../../models/table/ui.grid.data.model';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-common-table',
  templateUrl: './common-table.component.html',
  styleUrls: ['./common-table.component.css']
})
export class CommonTableComponent implements OnInit {
  @Input() getData: GetData;
  @Input() uiGridData: UiGridData;
  @Output() cellClickEvent: EventEmitter<any> = new EventEmitter<any>();

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  columns: string[];
  rows: any[][];
  editField: string;
  tripDataSource: MatTableDataSource<any[]>;
  pageSize: number;
  length: number;
  pageEvent: PageEvent;

  constructor(private gridDataService: GridDataService) {
  }

  ngOnInit(): void {
    this.getDataFromServer(undefined);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.tripDataSource.filter = filterValue.trim().toLowerCase();
  }

  onCellClick(e: any, row: any) {
    this.cellClickEvent.emit({row, mouseEvent: e});
  }

  changeValue(id: number, property: string, event: any) {
    this.editField = event.target.textContent;
  }

  onPageEvent(event: PageEvent) {
    this.getDataFromServer(event);
  }

  getDataFromServer(event: PageEvent) {
    if (event) {
      this.getData.parameters.set('page', event.pageIndex);
      this.getData.parameters.set('size', event.pageSize);
    } else {
      this.getData.parameters.set('page', 0);
      this.getData.parameters.set('size', 5);
      this.getData.parameters.set('sortBy', 'id');
    }


    this.gridDataService.getGridData(this.getData).subscribe(data => {
      if (data.message) {
        alert(data.message);
      } else {
        this.rows = data.body.data;
        this.columns = data.body.metaData.columns;

        const dataSource = new MatTableDataSource(this.rows);
        dataSource.sort = this.sort;
        dataSource.paginator = this.paginator;
        this.tripDataSource = dataSource;
        this.tripDataSource.paginator = this.paginator;
        this.length = data.body.metaData.totalCount;
        this.pageSize = data.body.data.length;
      }
    }, error => {
      alert(error);
    });
  }
}
