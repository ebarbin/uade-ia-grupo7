import { FavouriteOffer } from './models/favourite-offer.model';
import { FavouriteOfferService } from './services/favourite-offer.service';
import { PackageOfferHeader } from './../package-offer/models/package-offer-header.model';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HotelOfferRequest } from './../hotel-offer/models/hotel-offer-request.model';
import { AuthorizeStatus } from './../../shared/models/authorize-status.model';
import { HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { HotelOfferHeader } from './../hotel-offer/models/hotel-offer-header.model';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit, OnDestroy {

  constructor(
    private service:FavouriteOfferService,
    private toastr: ToastrService,
    private router: Router) { }

  favouriteOffers:FavouriteOffer[] = [];
  subs:Subscription;

  ngOnInit() {
    this.service.getFavourites().then((favouriteOffers:FavouriteOffer[])=>{
      this.favouriteOffers = favouriteOffers;
    });
    this.subs = this.service.favouriteChanges.subscribe((change:{id:number, type:string})=>{
      this.favouriteOffers = this.favouriteOffers.filter((value:FavouriteOffer) => {
        return  (change.type != value.type) || (change.type == value.type && change.id != value.id);
      });
    });
  }

  ngOnDestroy(){
    this.subs.unsubscribe();
  }

  onConfirmHotel(hoh:HotelOfferHeader){
    var request:HotelOfferRequest = new HotelOfferRequest(hoh.offerEnd, hoh.offerStart, null, null, hoh.roomCapacity, null, null);
    this.service.authorizeHotelReservation(hoh.id, request)
      .then((authorizeStatus:AuthorizeStatus)=>{
        if (authorizeStatus.status) {
          this.router.navigate(['home']);
        } else {
          this.toastr.error(authorizeStatus.description);
        }
      }).catch((res:HttpErrorResponse) => {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
  }

}
