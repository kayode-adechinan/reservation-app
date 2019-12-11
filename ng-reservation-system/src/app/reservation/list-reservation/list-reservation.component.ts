import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../reservation.service';
import { Reservation } from '../reservation';

@Component({
  selector: 'app-list-reservation',
  templateUrl: './list-reservation.component.html',
  styleUrls: ['./list-reservation.component.scss']
})
export class ListReservationComponent implements OnInit {

  reservations: Reservation[]

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {

    this.retrieveReservations();
    
  }

  retrieveReservations(){

    this.reservationService.retrieveReservation().subscribe(
      res => {
       console.log(res);
       this.reservations = res;
      },
      error => { console.log(error); }
    );

  }

  cancelReservation(reservationId:number){

    this.reservationService.deleteReservation(reservationId)
    .subscribe(res=>this.retrieveReservations(), err=>console.log(err));
    
  }



}
