import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedRoutingModule } from './shared-routing.module';
import { HeaderComponent } from './header/header.component';
import { JwtModule } from '@auth0/angular-jwt';


@NgModule({
  declarations: [HeaderComponent],
  imports: [
    CommonModule,
    SharedRoutingModule,
    JwtModule
  ]
})
export class SharedModule { }
