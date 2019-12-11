import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Trip } from "./trip";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class TripService {
  private baseUrl = "http://localhost:8080/api/v1/trips";

  constructor(private http: HttpClient) {}

  createTrip(trip: Trip) {

    return this.http.post(`${this.baseUrl}`, trip);
  }

  findTrip(trip: Trip) {
    return this.http.get<Array<Trip>>(
      `${this.baseUrl}/?leaveDate=${trip.leaveDate}&leaveFrom=${trip.leaveFrom}&to=${trip.to}`
    );
  }
}
