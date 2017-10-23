import { PackageAuthorizeRequest } from './../../models/package-authorize-request.model';
import { HotelAuthorizeRequest } from './../../models/hotel-authorize-request.model';
import { Constant } from './../../models/constant';
import { FavouriteOfferService } from './../../../home/favourite/services/favourite-offer.service';
import { PackageOfferRequest } from './../../../home/package-offer/models/package-offer-request.model';
import { HotelOfferRequest } from './../../../home/hotel-offer/models/hotel-offer-request.model';
import { HotelOfferHeader } from './../../../home/hotel-offer/models/hotel-offer-header.model';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from './../../../auth/services/auth.service';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-favourite-button',
  templateUrl: './favourite-button.component.html',
  styleUrls: ['./favourite-button.component.css']
})
export class FavouriteButtonComponent {

  @Input() offer: any;
  
  @Input() roomQuantity: number;
  @Input() quantityPeople: number;
  @Input() fromDate: Date;
  @Input() toDate: Date;

  constructor(
    private favouriteService: FavouriteOfferService,
    private toastr: ToastrService) { }
  
  markFavourite(offer: any){
    if (offer.type == Constant.HOTEL) {

      offer.roomQuantity = offer.roomQuantity ? offer.roomQuantity : this.roomQuantity;
      offer.fromDate = offer.offerStart ? offer.offerStart : this.offer.fromDate;
      offer.toDate = offer.offerEnd ? offer.offerEnd : this.toDate;

      var req = new HotelAuthorizeRequest(offer.roomQuantity, offer.fromDate, offer.toDate);
      this.favouriteService.markFavouriteHotel(offer, req)
        .then((result:boolean)=>{
          offer.favourite = result;
          if (result) this.toastr.success('Has agregado la oferta a favoritos.');
          else this.toastr.success('Has quitado la oferta de favoritos.');
        }).catch((res:HttpErrorResponse) => {
          this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
        });
    } else {

      offer.quantityPeople = offer.quantityPeople ? offer.quantityPeople : this.quantityPeople;
      
      var reqp = new PackageAuthorizeRequest(offer.quantityPeople);
      this.favouriteService.markFavouritePackage(offer, reqp)
        .then((result:boolean)=>{
          offer.favourite = result;
          if (result) this.toastr.success('Has agregado la oferta a favoritos.');
          else this.toastr.success('Has quitado la oferta de favoritos.');
        }).catch((res:HttpErrorResponse) => {
          this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
        });
    }
  }
}
