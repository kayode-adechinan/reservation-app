import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { UserService } from '../../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class CanActivateGuard implements CanActivate {

  constructor(private auth:UserService, private router: Router){

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    //return this.auth.isLogged();

    if (!this.auth.isLogged()) {
      this.router.navigate(['login-user']);
      return false;
    }
    return true;

  }
  
}
