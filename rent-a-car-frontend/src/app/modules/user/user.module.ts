import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserHomeComponent } from './user-home/user-home.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { CarsListComponent } from './cars/cars-list/cars-list.component';
import { CarsNewComponent } from './cars/cars-new/cars-new.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DataTablesModule } from 'angular-datatables';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [UserHomeComponent, UserDashboardComponent, CarsListComponent, CarsNewComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
    DataTablesModule,
    NgbModule
  ]
})
export class UserModule { }
