import { Offer } from './../../models/offer.interface';
import { HotelOfferHeader } from './../../../home/hotel-offer/models/hotel-offer-header.model';
import { FavouriteOfferService } from './../../services/favourite-offer.service';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from './../../../auth/services/auth.service';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent {

  @Input() offer: Offer;
  @Input() type: string;

  constructor(
    private favouriteService: FavouriteOfferService,
    private toastr: ToastrService) { }
  
  markFavourite(offer:Offer){
    if (this.type == 'hotel') {
      this.favouriteService.markHotelFavourite(offer)
        .then((result:boolean)=>{
          offer.favourite = result;
          if (result) this.toastr.success('Has agregado la oferta a favoritos.');
          else this.toastr.success('Has quitado la oferta de favoritos.');
        }).catch((res:HttpErrorResponse) => {
          this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
        });
    } else {

    }
  }
}
