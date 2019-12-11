import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080/api/auth";

  constructor(private http: HttpClient) { }


  create(user:User){
   return this.http.post(`${this.baseUrl}/signup`, user);

    /*this.http.post(`${this.baseUrl}/signup`, user)
    .subscribe(res => {
      this.login(user);

    },
    err => {
      console.log(err)
    });*/

  }

  login(user:User){
   return this.http.post<any>(`${this.baseUrl}/signin`, user);
  /*  .subscribe(res => {

      console.log(res)

      let token= 'Bearer '+res.bearerToken;
      console.log(token)
      localStorage.setItem('token', 'Bearer '+ token);

    },
    err => {
      console.log(err)
    });*/
  }

  
  isLogged(){
    let token = localStorage.getItem('token');
    return !(token === null);
  }

  checkIfEmalAlreadyExists(email:string){
     return this.http.get<any>(`${this.baseUrl}/existsByEmail/${email}/check`);
 
  }

 


}
