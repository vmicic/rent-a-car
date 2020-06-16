import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserHomeComponent } from './user-home/user-home.component';
import { RoleUserGuard } from 'src/app/shared/auth/guards/role-user.guard';
import { CarsListComponent } from './cars/cars-list/cars-list.component';
import { CarsNewComponent } from './cars/cars-new/cars-new.component';
import { CarsDetailsComponent } from './cars/cars-details/cars-details.component';
import { AdvertisementsListComponent } from './advertisements/advertisements-list/advertisements-list.component';
import { AdvertisementsNewComponent } from './advertisements/advertisements-new/advertisements-new.component';
import { NewApprovedComponent } from './reservations/new-approved/new-approved.component';


const routes: Routes = [
  { 
    path: '',
    component: UserHomeComponent,
    canActivate: [RoleUserGuard],
    data: {
      expectedRole: 'ROLE_USER'
    },
    children: [
      {
        path: '',
        canActivateChild: [RoleUserGuard],
        children: [
          { path: 'cars', component: CarsListComponent},
          { path: 'cars/new', component: CarsNewComponent},
          { path: 'cars/:id/advertisements/new', component: AdvertisementsNewComponent},
          { path: 'cars/:id/reservations/new', component: NewApprovedComponent},
          { path: 'cars/:id', component: CarsDetailsComponent},
          { path: 'advertisements', component: AdvertisementsListComponent}
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
