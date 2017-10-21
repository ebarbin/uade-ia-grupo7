import { PackageOfferRequest } from './../../../home/package-offer/models/package-offer-request.model';
import { HotelOfferRequest } from './../../../home/hotel-offer/models/hotel-offer-request.model';
import { Offer } from './../../models/offer.interface';
import { HotelOfferHeader } from './../../../home/hotel-offer/models/hotel-offer-header.model';
import { FavouriteOfferService } from './../../services/favourite-offer.service';
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

  @Input() offer: Offer;
  @Input() type: string;
  @Input() roomQuantity: number;
  @Input() fromDate: Date;
  @Input() toDate: Date;
  @Input() quantityPeople: number;
  
  constructor(
    private favouriteService: FavouriteOfferService,
    private toastr: ToastrService) { }
  
  markFavourite(offer:Offer){
    if (this.type == 'hotel') {
      var hotelRequest:HotelOfferRequest = new HotelOfferRequest(this.toDate, this.fromDate, null, null, this.roomQuantity, null, null);
      this.favouriteService.markFavouriteHotel(offer, hotelRequest)
        .then((result:boolean)=>{
          offer.favourite = result;
          if (result) this.toastr.success('Has agregado la oferta a favoritos.');
          else this.toastr.success('Has quitado la oferta de favoritos.');
        }).catch((res:HttpErrorResponse) => {
          this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
        });
    } else {
      var packageRequest:PackageOfferRequest = new PackageOfferRequest(null, null, null, this.quantityPeople, null, null);
      this.favouriteService.markFavouritePackage(offer, packageRequest)
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
