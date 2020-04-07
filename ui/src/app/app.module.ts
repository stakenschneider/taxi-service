import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {ProfileComponent} from './profile/profile.component';
import {Routes, RouterModule} from '@angular/router';
import {NotFoundComponent} from './not-found/not-found.component';
import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
import {TopBarComponent} from './top-bar/top-bar.component';
import {HomeComponent} from './home/home.component';
import {SignInComponent} from './auth/sign-in/sign-in.component';
import {SignUpComponent} from './auth/sign-up/sign-up.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {HttpClientModule} from '@angular/common/http';
import {RequestCarComponent} from './request-car/request-car.component';
import {DataService} from '../services/data.service';
import {StoreService} from '../services/store.service';
import {ClientService} from '../services/client.service';
import {DriverService} from '../services/driver.service';
import {TakeTripComponent} from './take-trip/take-trip.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { DialogComponent } from './dialog/dialog.component';
import { DialogHistoryTripComponent } from './dialog-history-trip/dialog-history-trip.component';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';

// определение маршрутов
const appRoutes: Routes = [
  {path: '', component: ProfileComponent},
  {path: 'sign-in', component: SignInComponent},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'fp', component: ForgotPasswordComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'request-car', component: RequestCarComponent},
  {path: 'take-trip', component: TakeTripComponent},
  {path: 'home', component: HomeComponent},
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
    DialogHistoryTripComponent
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
    MatInputModule
  ],
  providers: [
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
