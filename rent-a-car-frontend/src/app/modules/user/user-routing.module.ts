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
import { SearchFormComponent } from './search/search-form/search-form.component';
import { SearchResultComponent } from './search/search-result/search-result.component';
import { SearchCarDetailsComponent } from './search/search-car-details/search-car-details.component';
import { CartComponent } from './cart/cart.component';
import { ReceivedComponent } from './reservations/received/received.component';
import { RequestedComponent } from './reservations/requested/requested.component';
import { MessagesNewComponent } from './messages/messages-new/messages-new.component';
import { ShowAllComponent } from './messages/show-all/show-all.component';


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
          { path: 'advertisements', component: AdvertisementsListComponent},
          { path: 'search/result', component: SearchResultComponent},
          { path: 'search/result/:id', component: SearchCarDetailsComponent},
          { path: 'search', component: SearchFormComponent},
          { path: 'cart', component: CartComponent},
          { path: 'reservations/received', component: ReceivedComponent},
          { path: 'reservations', component: RequestedComponent},
          { path: 'messages', component:ShowAllComponent}
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
