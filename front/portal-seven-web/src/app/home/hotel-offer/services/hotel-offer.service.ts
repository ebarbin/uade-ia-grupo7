import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';

import { HotelOfferRequest } from '../models/hotel-offer-request.model';
import { HotelOfferHeader } from '../models/hotel-offer-header.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';

@Injectable()
export class HotelOfferService {

  public hotelOfferRequest:HotelOfferRequest;

  constructor(
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}

    search(request:HotelOfferRequest):Promise<HotelOfferHeader[]>{
      return this.httpClient.post('portal-seven-web/api/rest/hotel-offer/search', request)
        .map((response:PortalResponse)=>{
          if(response.success) {
            var results = <HotelOfferHeader[]>response.data;
            if (results.length == 0) this.toastr.info('No hay resultados.')
            return results;
          }
        }).toPromise();
    }
}
