import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {GetData} from '../../models/table/get.data.model';
import {GridDataService} from '../../services/grid.data.service';
import {MatTableDataSource} from '@angular/material/table';
import {Person} from '../../models/actor/person.model';
import {MatSort} from '@angular/material/sort';
import {Trip} from '../../models/trip.model';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-common-table',
  templateUrl: './common-table.component.html',
  styleUrls: ['./common-table.component.css']
})
export class CommonTableComponent implements OnInit {
  @Input() getData: GetData;
  columns: string[];
  rows: any[][];
  editField: string;
  tripDataSource: MatTableDataSource<any[]>;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

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
      }
    }, error => {
      alert(error);
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.tripDataSource.filter = filterValue.trim().toLowerCase();
  }

  changeValue(id: number, property: string, event: any) {
    this.editField = event.target.textContent;
  }
}
