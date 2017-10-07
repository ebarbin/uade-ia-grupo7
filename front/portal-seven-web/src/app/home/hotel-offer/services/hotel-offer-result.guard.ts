import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { HotelOfferService } from './hotel-offer.service';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class HotelOfferResultGuard implements CanActivate {

  constructor(private hotelOfferService: HotelOfferService, private toastr: ToastrService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
    if (this.hotelOfferService.getResults().length == 0) this.toastr.info('No hay resultados.');
    return this.hotelOfferService.getResults().length > 0;
  }
}
