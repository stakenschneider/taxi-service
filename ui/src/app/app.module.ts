import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {ProfileComponent} from './pages/profile/profile.component';
import {Routes, RouterModule} from '@angular/router';
import {NotFoundComponent} from './pages/not-found/not-found.component';
import {ForgotPasswordComponent} from './pages/auth/forgot-password/forgot-password.component';
import {TopBarComponent} from './components/top-bar/top-bar.component';
import {HomeComponent} from './pages/home/home.component';
import {DialogContentComponent, SignInComponent} from './pages/auth/sign-in/sign-in.component';
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
import {DialogComponent} from './components/dialog/dialog.component';
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
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {SnackBarComponent} from './components/snack-bar/snack-bar.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {GridDataService} from '../services/grid.data.service';
import {CommonTableComponent} from './components/common-table/common-table.component';
import {CdkTableModule} from '@angular/cdk/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatListModule} from '@angular/material/list';

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
  entryComponents: [DialogComponent, DialogContentComponent],
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
    DialogContentComponent,
    AdminComponent,
    SnackBarComponent,
    CommonTableComponent],
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
    MatSnackBarModule,
    MatToolbarModule,
    CdkTableModule,
    MatMenuModule,
    MatListModule
  ],
  providers: [
    AdminService,
    DriverService,
    AuthService,
    DataService,
    StoreService,
    ClientService,
    GridDataService],
  exports: [RouterModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
