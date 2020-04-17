import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {ProfileComponent} from './pages/profile/profile.component';
import {Routes, RouterModule} from '@angular/router';
import {NotFoundComponent} from './pages/not-found/not-found.component';
import {ForgotPasswordComponent} from './pages/auth/forgot-password/forgot-password.component';
import {TopBarComponent} from './top-bar/top-bar.component';
import {HomeComponent} from './pages/home/home.component';
import {SignInComponent} from './pages/auth/sign-in/sign-in.component';
import {SignUpComponent} from './pages/auth/sign-up/sign-up.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {HttpClientModule} from '@angular/common/http';
import {RequestCarComponent} from './pages/request-car/request-car.component';
import {DataService} from '../services/data.service';
import {StoreService} from '../services/store.service';
import {ClientService} from '../services/client.service';
import {DriverService} from '../services/driver.service';
import {TakeTripComponent} from './pages/take-trip/take-trip.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DialogComponent} from './dialog/dialog.component';
import {DialogHistoryTripComponent} from './dialog-history-trip/dialog-history-trip.component';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {AdminService} from '../services/admin.service';
import {AdminComponent} from './pages/admin/admin.component';
import {MatTabsModule} from '@angular/material/tabs';
import {TripTableComponent} from './tables/trip-table/trip-table.component';
import {PersonTableComponent} from './tables/person-table/person-table.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { SnackBarComponent } from './snack-bar/snack-bar.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';

const appRoutes: Routes = [
  {path: '', component: ProfileComponent},
  {path: 'sign-in', component: SignInComponent},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'fp', component: ForgotPasswordComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'request-car', component: RequestCarComponent},
  {path: 'take-trip', component: TakeTripComponent},
  {path: 'home', component: HomeComponent},
  {path: 'admin', component: AdminComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  entryComponents: [DialogComponent, DialogHistoryTripComponent],
  declarations: [
    AppComponent,
    ProfileComponent,
    NotFoundComponent,
    ForgotPasswordComponent,
    TopBarComponent,
    TakeTripComponent,
    HomeComponent,
    SignInComponent,
    SignUpComponent,
    RequestCarComponent,
    TakeTripComponent,
    DialogComponent,
    DialogHistoryTripComponent,
    AdminComponent,
    TripTableComponent,
    PersonTableComponent,
    SnackBarComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSortModule,
    MatTableModule,
    MatProgressBarModule,
    MatButtonToggleModule,
    MatExpansionModule,
    MatIconModule,
    MatInputModule,
    MatTabsModule,
    MatPaginatorModule,
    MatCheckboxModule,
    MatSnackBarModule
  ],
  providers: [
    AdminService,
    DriverService,
    AuthService,
    DataService,
    StoreService,
    ClientService],
  exports: [RouterModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
