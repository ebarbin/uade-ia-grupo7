import { AuthService } from './../../../auth/services/auth.service';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class HotelOfferGuard implements CanActivate {

  constructor(
    private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return !this.authService.isAdmin();
  }

}
