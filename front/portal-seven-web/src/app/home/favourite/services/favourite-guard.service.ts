import { ToastrService } from 'ngx-toastr';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { FavouriteOfferService } from './favourite-offer.service';
import { AuthService } from '../../../auth/services/auth.service';

@Injectable()
export class FavouriteGuard implements CanActivate  {

  constructor(
    private toastr: ToastrService,
    private authService: AuthService, 
    private favouriteOfferService:FavouriteOfferService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isAdmin()){
      this.toastr.info('El usuario admin no puede realizar esta operación.')
      return false;
    } else {
      return this.favouriteOfferService.canActivate();
    }
  }
}
