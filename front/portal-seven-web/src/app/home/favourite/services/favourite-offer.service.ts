import { Observable } from 'rxjs/Observable';
import { PackageAuthorizeRequest } from './../../../shared/models/package-authorize-request.model';
import { HotelAuthorizeRequest } from './../../../shared/models/hotel-authorize-request.model';
import { FavouriteOffer } from './../models/favourite-offer.model';
import { AuthorizeStatus } from './../../../shared/models/authorize-status.model';
import { PackageOfferHeader } from './../../package-offer/models/package-offer-header.model';
import { HotelOfferHeader } from './../../hotel-offer/models/hotel-offer-header.model';
import { PackageOfferRequest } from './../../package-offer/models/package-offer-request.model';
import { PortalResponse } from './../../../shared/models/portal-response.model';
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

  markFavouriteHotel(offer:any, request:HotelAuthorizeRequest):Promise<boolean>{
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

  markFavouritePackage(offer:any, request:PackageAuthorizeRequest):Promise<boolean>{
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

  getFavourites():Promise<FavouriteOffer[]>{
    return Observable.of(this.favourites).toPromise();
  }

  private favourites:FavouriteOffer[] = [];

  canActivate():Promise<boolean>{
    return this.httpClient.get('portal-seven-web/api/rest/favourite-offer/' + this.authService.getUser().id)
      .map((response:PortalResponse)=>{
        if (response.success){
          this.favourites = <FavouriteOffer[]> response.data;
          if (this.favourites.length == 0) this.toastr.info('No hay favoritos.');
          return this.favourites.length > 0;
        } else {
          this.toastr.error(response.errorMessage);
          this.favourites = [];
          return false;
        }
    }).toPromise();
  }
}
