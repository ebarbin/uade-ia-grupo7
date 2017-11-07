import { ToastrService } from 'ngx-toastr';
import { ConfigurationService } from './../../home/configuration/services/configuration.service';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class SystemConfigurationGuard implements CanActivate {

  constructor(private toastr: ToastrService, private configService:ConfigurationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.configService.isConfigured();
  }
}
