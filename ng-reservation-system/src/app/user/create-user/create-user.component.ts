import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { UniqueEmail, emailExist } from 'src/app/commons/validators/unique-email.validator';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {

  createUserForm: FormGroup;

  submitted = false;


  constructor(private userService: UserService, private fb:FormBuilder, private router: Router) { }

  ngOnInit() {
    this.createUserForm = this.fb.group({
      email:['', [Validators.required, Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')], 
      [emailExist(this.userService)]],
      password:['', [Validators.required]]
    })
  }

  // convenience getter for easy access to form fields
  get f() { return this.createUserForm.controls; }

  onSubmit(form:FormGroup){
    console.log(form);

    /*this.submitted = true;

    // stop here if form is invalid
    if (this.createUserForm.invalid) {
        return;
    }*/

    this.userService.create(form.value)
    .subscribe(res => {

          this.userService.login(form.value)
          
          .subscribe(res => {

            console.log(res)
      
            let token= 'Bearer '+res.bearerToken;
            console.log(token)
            localStorage.setItem('token', token);
            localStorage.setItem('user', res.user);
            this.router.navigate(['/']);

      
          },
          err => {
            console.log(err)
          });

    },
    err => {
      console.log(err)
    })
    
  }

}
