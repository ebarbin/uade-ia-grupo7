import { Observable } from 'rxjs/Observable';
import { Reservation } from './../models/reservation.model';
import { ToastrService } from 'ngx-toastr';
import { PortalResponse } from './../../../shared/models/portal-response.model';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './../../../auth/services/auth.service';
import { Injectable } from '@angular/core';

@Injectable()
export class ReservationService {

  constructor(
    private toastr:ToastrService,
    private httpClient:HttpClient, 
    private authService:AuthService) { }

  reservations:Reservation[] = [];

  getReservations():Promise<Reservation[]>{
    return Observable.of(this.reservations).toPromise();
  }

  canActivate():Promise<boolean>{
    return this.httpClient.get('portal-seven-web/api/rest/reserve-history/' + this.authService.getUser().id)
      .map((response:PortalResponse)=>{
        if (response.success){
          this.reservations = <Reservation[]> response.data;
          if (this.reservations.length == 0) this.toastr.info('No hay reservas.');
          return this.reservations.length > 0;
        } else {
          this.toastr.error(response.errorMessage);
          this.reservations = [];
          return false;
        }
    }).toPromise();
  }
}
