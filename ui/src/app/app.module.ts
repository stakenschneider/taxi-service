import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';

import { RouterModule} from '@angular/router';
import { NotFoundComponent } from './not-found/not-found.component';
import { LogOutComponent } from './log-out/log-out.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { HomeComponent } from './home/home.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {OktaAuthModule} from '@okta/okta-angular';
import {AuthInterceptor} from './okta/auth.interceptor';

const config = {
  issuer: 'https://dev-151550/oauth2/default',
  redirectUri: 'http://localhost:4200/implicit/callback',
  clientId: '0oa471givxVyMkXtX4x6',
  pkce: true
}

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    NotFoundComponent,
    LogOutComponent,
    ForgotPasswordComponent,
    TopBarComponent,
    HomeComponent,
    SignInComponent,
    SignUpComponent,
    AppRoutingModule
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    OktaAuthModule.initAuth(config)
  ],
  providers: [AuthService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  exports: [RouterModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
