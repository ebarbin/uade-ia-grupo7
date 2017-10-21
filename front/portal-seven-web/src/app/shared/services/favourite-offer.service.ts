import { AuthorizeStatus } from './../models/authorize-status.model';
import { Subject } from 'rxjs/Subject';
import { HotelOfferRequest } from './../../home/hotel-offer/models/hotel-offer-request.model';
import { ToastrService } from 'ngx-toastr';
import { Offer } from './../models/offer.interface';
import { PortalResponse } from './../models/portal-response.model';
import { HotelOfferHeader } from './../../home/hotel-offer/models/hotel-offer-header.model';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './../../auth/services/auth.service';

import { Injectable } from '@angular/core';

@Injectable()
export class FavouriteOfferService {

  favouriteHotelChanges:Subject<number> = new Subject<number>();

  constructor(
    private authService: AuthService,
    private toastr: ToastrService,
    private httpClient: HttpClient) { }

  markFavouriteHotel(offer:Offer, request:HotelOfferRequest):Promise<boolean>{
    return this.httpClient.put('portal-seven-web/api/rest/favourite-offer/hotel/' + 
      offer.id + '/' + this.authService.getUser().id, request)
      .map((response:PortalResponse)=>{
        if (response.success){
          this.favouriteHotelChanges.next(offer.id);
          return <boolean> response.data;
        } else {
          this.toastr.error(response.errorMessage);
          return false;
        }
    }).toPromise();
  }

  getFavouriteHotels():Promise<HotelOfferHeader[]>{
    return this.httpClient.get('portal-seven-web/api/rest/favourite-offer/hotel/' + this.authService.getUser().id)
      .map((response:PortalResponse)=>{
        if (response.success){
          return <HotelOfferHeader[]> response.data;
        } else {
          this.toastr.error(response.errorMessage);
          return [];
        }
    }).toPromise();
  }

  authorizeReservation(hotelOfferId:number, request:HotelOfferRequest):Promise<AuthorizeStatus>{
    return this.httpClient.put('portal-seven-web/api/rest/hotel-offer/authorize/' + 
    hotelOfferId, request)
      .map((response:PortalResponse)=>{
        if(response.success) {
          return <AuthorizeStatus>response.data;
        } else {
          this.toastr.error(response.errorMessage);
          return null;
        }
      }).toPromise();
  }
}
