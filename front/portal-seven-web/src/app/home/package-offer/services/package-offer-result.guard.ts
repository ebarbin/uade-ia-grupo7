import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PackageOfferService } from './package-offer.service';

@Injectable()
export class PackageOfferResultGuard implements CanActivate {

  constructor(private packageOfferService: PackageOfferService, private toastr: ToastrService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
    if (this.packageOfferService.getResults().length == 0) this.toastr.info('No hay resultados.');
    return this.packageOfferService.getResults().length > 0;
  }
}
