import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountModule } from './modules/account/account.module';
import { HomeModule } from './modules/home/home.module';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { SharedModule } from './shared/shared.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AccountModule,
    HomeModule,
    SharedModule,
    HttpClientModule
  ],
  providers: [
  {
    provide: JWT_OPTIONS, useValue: JWT_OPTIONS
  },
  JwtHelperService],
  bootstrap: [AppComponent]
})
export class AppModule { }
