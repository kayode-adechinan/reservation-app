import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Reservation } from './reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {


  private baseUrl = "http://localhost:8080/api/v1/reservations";

  constructor(private http: HttpClient) { }

  createReservation(reservation:Reservation){
    return this.http.post(`${this.baseUrl}`, reservation);
  }

  retrieveReservation(){
    let user = localStorage.getItem("user");
    return this.http.get<Reservation[]>(`${this.baseUrl}/?userId=${user}`);
  }

  deleteReservation(id:number){
    return this.http.delete(`${this.baseUrl}/${id}`);
  }


}
