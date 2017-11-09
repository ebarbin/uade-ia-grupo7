import { ReservationService } from './reservation.service';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from './../../../auth/services/auth.service';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class ReserveHistoryGuard implements CanActivate {

  constructor(
    private toastr: ToastrService, 
    private authService:AuthService,
    private reservationService: ReservationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isAdmin()){
      this.toastr.info('El usuario admin no puede realizar esta operación.')
      return false;
    } else {
      return this.reservationService.canActivate();
    }
  }
}
