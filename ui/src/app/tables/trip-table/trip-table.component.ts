// import {Component, OnInit, ViewChild} from '@angular/core';
// import {Trip} from '../../../models/trip.model';
// import {MatTableDataSource} from '@angular/material/table';
// import {MatSort} from '@angular/material/sort';
// import {Router} from '@angular/router';
// import {DriverService} from '../../../services/driver.service';
// import {AdminService} from '../../../services/admin.service';
// import {MatPaginator} from '@angular/material/paginator';
//
// @Component({
//   selector: 'app-trip-table',
//   templateUrl: './trip-table.component.html',
//   styleUrls: ['./trip-table.component.css']
// })
// export class TripTableComponent implements OnInit {
//   getDataForDriver: any = {};
//   parametersForDriver: Map<string, any> = new Map<string, any>();
//
//   // public tripsArray: Trip[];
//   // tripsColumns: string[] = ['id', 'price', 'tripRate', 'paymentMethod', 'status', 'rating', 'dateOfCreation',
//   //   'dateOfCompletion', 'client', 'driver', 'startAddress', 'finishAddress'];
//   //
//   // tripDataSource: MatTableDataSource<Trip>;
//   // @ViewChild(MatSort, {static: true}) sort: MatSort;
//   // @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
//
//   constructor(private router: Router, private driverService: DriverService, private adminService: AdminService) {
//   }
//
//   ngOnInit(): void {
//     this.getDataForTrips.dataName = 'tripTable';
//     this.parametersForTrips.set('for', 'ADMIN');
//     this.getDataForTrips.parameters = this.parametersForTrips;
//
//
//     this.showAllTrips();
//   }
//
//   // showAllTrips() {
//   //   this.adminService.getAllTrips().subscribe(
//   //     data => {
//   //       this.tripsArray = data.body;
//   //       // tslint:disable-next-line:max-line-length
//   //       const month = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
//   //       this.tripsArray.forEach(trip => {
//   //         trip.client.rating = +trip.client.rating.toFixed(1);
//   //         if (trip.driver) {
//   //           trip.driver.rating = +trip.driver.rating.toFixed(1);
//   //         }
//   //       });
//   //
//   //       const dataSource = new MatTableDataSource(this.tripsArray);
//   //       dataSource.sort = this.sort;
//   //       dataSource.paginator = this.paginator;
//   //       this.tripDataSource = dataSource;
//   //       this.tripDataSource.paginator = this.paginator;
//   //     }, error => alert(error)
//   //   );
//   // }
//
//   // applyFilter(event: Event) {
//   //   const filterValue = (event.target as HTMLInputElement).value;
//   //   this.tripDataSource.filter = filterValue.trim().toLowerCase();
//   // }
// }
