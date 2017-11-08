import { ToastrService } from 'ngx-toastr';
import { AuthService } from './../../../auth/services/auth.service';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class HotelOfferGuard implements CanActivate {

  constructor(
    private toastr: ToastrService,
    private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isAdmin()){
      this.toastr.info('El usuario admin no puede realizar esta operación.')
      return false;
    } else {
      return true;
    }
  }

}
