import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TripComponent } from './trip/trip/trip.component';
import { CreateTripComponent } from './trip/create-trip/create-trip.component';
import { SearchTripComponent } from './trip/search-trip/search-trip.component';
import { ReservationComponent } from './reservation/reservation/reservation.component';
import { CreateReservationComponent } from './reservation/create-reservation/create-reservation.component';
import { ListReservationComponent } from './reservation/list-reservation/list-reservation.component';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { LoginUserComponent } from './user/login-user/login-user.component';
import { NavbarComponent } from './commons/navbar/navbar.component';
import { CanActivateGuard } from './commons/guards/can-activate.guard';
import { TokenInterceptorService } from './commons/interceptors/token-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    TripComponent,
    CreateTripComponent,
    SearchTripComponent,
    ReservationComponent,
    CreateReservationComponent,
    ListReservationComponent,
    CreateUserComponent,
    LoginUserComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  
  ],
  providers: [CanActivateGuard,
  
    { provide: HTTP_INTERCEPTORS, useClass: 
      TokenInterceptorService, multi: true }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
