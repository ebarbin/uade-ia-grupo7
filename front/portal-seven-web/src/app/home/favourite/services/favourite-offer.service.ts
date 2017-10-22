import { FavouriteOffer } from './../models/favourite-offer.model';
import { AuthorizeStatus } from './../../../shared/models/authorize-status.model';
import { PackageOfferHeader } from './../../package-offer/models/package-offer-header.model';
import { HotelOfferHeader } from './../../hotel-offer/models/hotel-offer-header.model';
import { PackageOfferRequest } from './../../package-offer/models/package-offer-request.model';
import { PortalResponse } from './../../../shared/models/portal-response.model';
import { Offer } from './../../../shared/models/offer.interface';
import { HotelOfferRequest } from './../../hotel-offer/models/hotel-offer-request.model';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from './../../../auth/services/auth.service';
import { Subject } from 'rxjs/Subject';
import { Injectable } from '@angular/core';

@Injectable()
export class FavouriteOfferService {

  favouriteChanges:Subject<{id:number, type:string}> = new Subject<{id:number, type:string}>();
  favouriteOfferSelected:FavouriteOffer;

  constructor(
    private authService: AuthService,
    private toastr: ToastrService,
    private httpClient: HttpClient) { }

  setFavouriteOfferSelected(favouriteOfferSelected:FavouriteOffer){
    this.favouriteOfferSelected = favouriteOfferSelected;
  }

  getFavouriteOfferSelected():FavouriteOffer{
    return this.favouriteOfferSelected;
  }

  markFavouriteHotel(offer:Offer, request:HotelOfferRequest):Promise<boolean>{
    return this.httpClient.put('portal-seven-web/api/rest/favourite-offer/hotel/' + 
      offer.id + '/' + this.authService.getUser().id, request)
      .map((response:PortalResponse)=>{
        if (response.success){
          this.favouriteChanges.next({id:offer.id, type:'HOTEL'});
          return <boolean> response.data;
        } else {
          this.toastr.error(response.errorMessage);
          return false;
        }
    }).toPromise();
  }

  markFavouritePackage(offer:Offer, request:PackageOfferRequest):Promise<boolean>{
    return this.httpClient.put('portal-seven-web/api/rest/favourite-offer/package/' + 
      offer.id + '/' + this.authService.getUser().id, request)
      .map((response:PortalResponse)=>{
        if (response.success){
          this.favouriteChanges.next({id:offer.id, type:'PACKAGE'});
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

  getFavouritePackages():Promise<PackageOfferHeader[]>{
    return this.httpClient.get('portal-seven-web/api/rest/favourite-offer/package/' + this.authService.getUser().id)
      .map((response:PortalResponse)=>{
        if (response.success){
          return <PackageOfferHeader[]> response.data;
        } else {
          this.toastr.error(response.errorMessage);
          return [];
        }
    }).toPromise();
  }

  getFavourites():Promise<FavouriteOffer[]>{
    return this.httpClient.get('portal-seven-web/api/rest/favourite-offer/' + this.authService.getUser().id)
      .map((response:PortalResponse)=>{
        if (response.success){
          return <FavouriteOffer[]> response.data;
        } else {
          this.toastr.error(response.errorMessage);
          return [];
        }
    }).toPromise();
  }

  authorizeHotelReservation(hotelOfferId:number, request:HotelOfferRequest):Promise<AuthorizeStatus>{
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
