import { Component, OnInit } from '@angular/core';
import { Trip } from '../trip';
import { TripService } from '../trip.service';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ReservationService } from 'src/app/reservation/reservation.service';
import { Reservation } from 'src/app/reservation/reservation';

@Component({
  selector: 'app-search-trip',
  templateUrl: './search-trip.component.html',
  styleUrls: ['./search-trip.component.scss']
})
export class SearchTripComponent implements OnInit {

  searchTripForm: FormGroup;
  trips: Trip[];


  constructor(private tripService:TripService, private reservationService:ReservationService, private fb: FormBuilder) { }

  ngOnInit() {
    this.searchTripForm = this.fb.group({
      leaveDate:['', [Validators.required]],
      leaveFrom: ['', [Validators.required]],
      to: ['', Validators.required],
    })
  }


  onSubmit(form: FormGroup){
    console.log(form);
    
    this.tripService.findTrip(form.value).subscribe(
      res => {
       console.log(res);
       this.trips = res;
      },
      error => { console.log(error); }
    )


  }


  book(trip:number){

    let user = parseInt(localStorage.getItem("user"));
    let reservation = new Reservation(trip, user)
    this.reservationService.createReservation(reservation)
    .subscribe(res => console.log(res), error => console.log(error));

  }

}
