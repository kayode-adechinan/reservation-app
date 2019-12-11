import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.scss']
})
export class LoginUserComponent implements OnInit {

  loginUserForm: FormGroup;

  constructor(private userService: UserService, private fb:FormBuilder, private router: Router) { }

  ngOnInit() {
    this.loginUserForm = this.fb.group({
      email:['', [Validators.required, Validators.email]],
      password:['', [Validators.required]]
    })
  }

  onSubmit(form:FormGroup){
    console.log(form);

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

  }

}
