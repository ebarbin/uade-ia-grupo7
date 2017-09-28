import { Subject } from 'rxjs/Subject';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';

import { HotelOfferRequest } from '../models/hotel-offer-request.model';
import { HotelOfferHeader } from '../models/hotel-offer-header.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';
import { HotelOffer } from '../models/hotel-offer.model';

@Injectable()
export class HotelOfferService {

  resultsChanged:Subject<HotelOfferHeader[]> = new Subject;

  constructor(
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}

    getDetail(hotelOfferHeader:HotelOfferHeader):Promise<HotelOffer>{
      return this.httpClient.get('portal-seven-web/api/rest/hotel-offer/detail/' + hotelOfferHeader.id)
      .map((response:PortalResponse)=>{
        if(response.success) {
          var hotelOffer = <HotelOffer>response.data;

          hotelOffer.hotel.rooms = hotelOffer.hotel.rooms.filter((room)=>{
            return room.id != hotelOffer.room.id;
          });

          return hotelOffer;
        }
      }).toPromise();
    }

    search(request:HotelOfferRequest):Promise<HotelOfferHeader[]>{
      return this.httpClient.post('portal-seven-web/api/rest/hotel-offer/search', request)
        .map((response:PortalResponse)=>{
          if(response.success) {
            var results = <HotelOfferHeader[]>response.data;
            if (results.length == 0) this.toastr.info('No hay resultados.')
            this.resultsChanged.next(results);
            return results;
          }
        }).toPromise();
    }
}
