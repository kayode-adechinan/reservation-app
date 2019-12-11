import { Component, OnInit } from '@angular/core';
import { Trip } from '../trip';
import { TripService } from '../trip.service';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ValidateUrl } from 'src/app/commons/validators/url.validator';

@Component({
  selector: 'app-create-trip',
  templateUrl: './create-trip.component.html',
  styleUrls: ['./create-trip.component.scss']
})
export class CreateTripComponent implements OnInit {


  createTripForm: FormGroup;

  constructor(private tripService:TripService, private fb: FormBuilder) { }

  ngOnInit() {
    this.createTripForm = this.fb.group({
      leaveDate:['', [Validators.required]],
      leaveFrom: ['', [Validators.required]],
      to: ['', Validators.required],
     // websiteUrl: ['', [Validators.required, ValidateUrl]],

    })
  }

  onSubmit(form: FormGroup){
    console.log(form);
    let user = parseInt(localStorage.getItem("user"));
    let trip = new Trip(form.value.leaveDate, 
              form.value.leaveFrom, form.value.to, user);
    this.tripService.createTrip(trip)
    .subscribe(data => console.log(data), error => console.log(error));
  }

}
