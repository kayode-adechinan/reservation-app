import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchTripComponent } from './trip/search-trip/search-trip.component';
import { CreateTripComponent } from './trip/create-trip/create-trip.component';
import { CreateReservationComponent } from './reservation/create-reservation/create-reservation.component';
import { ListReservationComponent } from './reservation/list-reservation/list-reservation.component';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { LoginUserComponent } from './user/login-user/login-user.component';
import { CanActivateGuard } from './commons/guards/can-activate.guard';


const routes: Routes = [

  { path: '', pathMatch: 'full', redirectTo: 'search-trip' },
  { path: 'search-trip', component: SearchTripComponent },
  { path: 'create-trip', component: CreateTripComponent, canActivate: [CanActivateGuard]},
  { path: 'create-reservation', component: CreateReservationComponent },
  { path: 'list-reservation', component: ListReservationComponent, canActivate: [CanActivateGuard] },
  { path: 'create-user', component: CreateUserComponent },
  { path: 'login-user', component: LoginUserComponent },

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

