import { AuthService } from './../../../auth/services/auth.service';
import { HttpClient } from '@angular/common/http';
import { HotelOfferHeader } from './../models/hotel-offer-header.model';
import { PortalResponse } from './../../../shared/models/portal-response.model';
import { PortalUser } from './../../user-profile/models/portal-user.model';
import { Injectable } from '@angular/core';

@Injectable()
export class FavoriteHotelOfferService {

  constructor(
    private authService: AuthService,
    private httpClient: HttpClient) { }

  markFavorite(hoh:HotelOfferHeader):Promise<boolean>{
    return this.httpClient.put('portal-seven-web/api/rest/favorite-hotel-offer/' + hoh.id + '/' + this.authService.getUser().id, null)
      .map((response:PortalResponse)=>{
        if (response.success){
          return <boolean> response.data;
        } else {
         return false;
        }
    }).toPromise();
  }

  isFavorite(hoh:HotelOfferHeader):Promise<boolean>{
    return this.httpClient.get('portal-seven-web/api/rest/favorite-hotel-offer/' + hoh.id + '/' + this.authService.getUser().id)
      .map((response:PortalResponse)=>{
        return response.success;
    }).toPromise();
  }
}
