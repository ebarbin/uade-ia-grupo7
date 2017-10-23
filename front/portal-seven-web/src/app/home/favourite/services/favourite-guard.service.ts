import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { FavouriteOfferService } from './favourite-offer.service';
import { AuthService } from '../../../auth/services/auth.service';

@Injectable()
export class FavouriteGuard implements CanActivate  {

  constructor(
    private authService: AuthService, 
    private favouriteOfferService:FavouriteOfferService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isAdmin()) return false;
    return this.favouriteOfferService.canActivate();
  }
}
