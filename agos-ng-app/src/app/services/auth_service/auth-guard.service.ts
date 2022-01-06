import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot,RouterStateSnapshot } from '@angular/router';
import {AuthService} from "./auth.service";


@Injectable()
export class AuthGuardService implements CanActivate {


  constructor(private router:Router, private authService : AuthService) {
  }

  canActivate(): boolean {
    let roles = localStorage.getItem('roles');
    // @ts-ignore
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }

}
