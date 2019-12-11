import { Trip } from '../trip/trip';

export class Reservation {
    id:number;
    trip: number;
    user: number;

    tripLeaveDate: string;
    tripLeaveFrom: string;
    tripTo:string;

    /*constructor(trip: number, user: number) {
        this.trip = trip;
        this.user = user;
    }*/

    constructor(trip:number, user:number){
        this.trip = trip
    }
}
