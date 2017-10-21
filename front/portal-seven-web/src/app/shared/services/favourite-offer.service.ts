import { HotelOfferService } from './../../home/hotel-offer/services/hotel-offer.service';
import { Offer } from './../models/offer.interface';
import { PortalResponse } from './../models/portal-response.model';
import { HotelOfferHeader } from './../../home/hotel-offer/models/hotel-offer-header.model';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './../../auth/services/auth.service';

import { Injectable } from '@angular/core';

@Injectable()
export class FavouriteOfferService {

  constructor(
    private hotelOfferService:HotelOfferService,
    private authService: AuthService,
    private httpClient: HttpClient) { }

    markHotelFavourite(offer:Offer):Promise<boolean>{
      return this.httpClient.put('portal-seven-web/api/rest/favourite-offer/hotel/' + 
        offer.id + '/' + this.authService.getUser().id, this.hotelOfferService.getFilterRequest())
        .map((response:PortalResponse)=>{
          if (response.success){
            return <boolean> response.data;
          } else {
          return false;
          }
      }).toPromise();
  }
}
