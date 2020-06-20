import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { RoleUserGuard } from 'src/app/shared/auth/guards/role-user.guard';
import { CarBrandComponent } from './car-brand/car-brand.component';
import { CarClassComponent } from './car-class/car-class.component';
import { CarModelComponent } from './car-model/car-model.component';
import { FuelTypeComponent } from './fuel-type/fuel-type.component';
import { TransmissionTypeService } from './services/transmission-type.service';
import { TransmissionTypeComponent } from './transmission-type/transmission-type.component';
import { UsersComponent } from './users/users.component';
import { ShowAllRatingsComponent } from './ratings/show-all-ratings/show-all-ratings.component';


const routes: Routes = [
  {
  path: '',
  component: AdminHomeComponent,
  canActivate: [RoleUserGuard],
  data: {
     expectedRole: 'ROLE_ADMINISTRATOR'
  },
  children: [
    {
      path: '',
      canActivateChild: [RoleUserGuard],
      children: [
        { path: 'car-brand', component: CarBrandComponent},
        { path: 'car-class', component: CarClassComponent},
        { path: 'car-model', component: CarModelComponent},
        { path: 'fuel-type', component: FuelTypeComponent},
        { path: 'transmission-type', component: TransmissionTypeComponent},
        { path: 'users', component: UsersComponent},
        { path: 'ratings', component:ShowAllRatingsComponent}
      ]
    }
  ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
