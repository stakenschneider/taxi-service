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
  @Output() paginatorEvent: EventEmitter<any> = new EventEmitter<any>();

  columns: string[];
  rows: any[][];
  editField: string;
  tripDataSource: MatTableDataSource<any[]>;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  pageSize: number;
  length: number;
  pageEvent: PageEvent;

  constructor(private gridDataService: GridDataService) {
  }

  ngOnInit(): void {
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
    this.paginatorEvent.emit(event);
  }
}
