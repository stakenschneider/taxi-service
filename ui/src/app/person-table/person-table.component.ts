import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {DriverService} from '../../services/driver.service';
import {AdminService} from '../../services/admin.service';
import {Driver} from '../../models/actor/driver.model';
import {Client} from '../../models/actor/client.model';
import {Person} from '../../models/actor/person.model';
import {element} from 'protractor';

@Component({
  selector: 'app-person-table',
  templateUrl: './person-table.component.html',
  styleUrls: ['./person-table.component.css']
})
export class PersonTableComponent implements OnInit {
  @Input() roleName: string;
  driversArray: Driver[];
  clientsArray: Client[];
  columns: string[] = ['id', 'firstName', 'lastName', 'rating', 'email', 'phoneNumber', 'edit'];

  dataSource: MatTableDataSource<Person>;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  editField: string;

  constructor(private driverService: DriverService, private adminService: AdminService) {
  }

  ngOnInit(): void {
    switch (this.roleName) {
      case 'driver':
        this.showAllDrivers();
        break;
      case 'client':
        this.showAllClients();
        break;
    }
  }

  showAllClients() {
    this.adminService.getAllClients().subscribe(
      data => {
        this.clientsArray = data.body;
        this.clientsArray.forEach(client => {
          client.rating = +client.rating.toFixed(1);
        });
        const dataSource = new MatTableDataSource(this.clientsArray);
        dataSource.sort = this.sort;
        this.dataSource = dataSource;
      }, error => alert(error)
    );
  }

  showAllDrivers() {
    this.adminService.getAllDrivers().subscribe(
      data => {
        this.driversArray = data.body;
        this.driversArray.forEach(driver => {
          driver.rating = +driver.rating.toFixed(1);
        });
        const dataSource = new MatTableDataSource(this.driversArray);
        dataSource.sort = this.sort;
        this.dataSource = dataSource;
      }, error => alert(error)
    );
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  deletePerson(person: any) {
    switch (this.roleName) {
      case 'driver':
        this.driversArray.splice(this.driversArray.indexOf(person), 1);
        this.dataSource = new MatTableDataSource(this.driversArray);
        break;
      case 'client':
        this.clientsArray.splice(this.clientsArray.indexOf(person), 1);
        this.dataSource = new MatTableDataSource(this.clientsArray);
        break;
    }
  }

  updateList(person: any, property: string, event: any) {
    const editField = event.target.textContent;
    // TODO if property was into credentials f.e. [property] does not work
    switch (this.roleName) {
      case 'driver':
        this.driversArray[this.driversArray.indexOf(person)][property] = editField;
        break;
      case 'client':
        this.clientsArray[this.clientsArray.indexOf(person)][property] = editField;
        break;
    }
  }

  changeValue(id: number, property: string, event: any) {
    this.editField = event.target.textContent;
  }
}
