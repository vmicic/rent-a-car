import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CarBrandComponent } from './car-brand/car-brand.component';
import { CarClassComponent } from './car-class/car-class.component';
import { CarModelComponent } from './car-model/car-model.component';
import { FuelTypeComponent } from './fuel-type/fuel-type.component';
import { TransmissionTypeComponent } from './transmission-type/transmission-type.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DataTablesModule } from 'angular-datatables';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [AdminHomeComponent, CarBrandComponent, CarClassComponent, CarModelComponent, FuelTypeComponent, TransmissionTypeComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    DataTablesModule,
    NgbModule
  ]
})
export class AdminModule { }
