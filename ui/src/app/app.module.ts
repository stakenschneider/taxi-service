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

// определение маршрутов
const appRoutes: Routes = [
  {path: '', component: ProfileComponent},
  {path: 'sign-in', component: SignInComponent},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'fp', component: ForgotPasswordComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'request-car', component: RequestCarComponent},
  {path: 'home', component: HomeComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    NotFoundComponent,
    ForgotPasswordComponent,
    TopBarComponent,
    HomeComponent,
    SignInComponent,
    SignUpComponent,
    RequestCarComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [AuthService,
    DataService],
  exports: [RouterModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
