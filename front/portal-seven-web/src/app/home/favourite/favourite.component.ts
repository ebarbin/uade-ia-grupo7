import { AuthService } from './../../auth/services/auth.service';
import { PackageAuthorizeRequest } from './../../shared/models/package-authorize-request.model';
import { PackageOfferService } from './../package-offer/services/package-offer.service';
import { HotelAuthorizeRequest } from './../../shared/models/hotel-authorize-request.model';
import { HotelOfferService } from './../hotel-offer/services/hotel-offer.service';
import { Constant } from './../../shared/models/constant';
import { FavouriteOffer } from './models/favourite-offer.model';
import { FavouriteOfferService } from './services/favourite-offer.service';
import { PackageOfferHeader } from './../package-offer/models/package-offer-header.model';
import { Router, ActivatedRoute } from '@angular/router';
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
    private authService: AuthService,
    private service:FavouriteOfferService,
    private poService: PackageOfferService,
    private hoService: HotelOfferService,
    private toastr: ToastrService,
    private router: Router, private route: ActivatedRoute ) { }

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
      if (this.favouriteOffers.length == 0) this.router.navigate(['home']);
    });
  }

  ngOnDestroy(){
    this.subs.unsubscribe();
  }

  onConfirmHotel(fo:FavouriteOffer){
    
    var authoReq = new HotelAuthorizeRequest();
    authoReq.fromDate = fo.offerStart;
    authoReq.toDate = fo.offerEnd;
    authoReq.roomQuantity = fo.quantityCapacity;
    authoReq.portalUser = this.authService.getUser();

    this.hoService.authorizeReservation(fo.id, authoReq)
    .then((authorizeStatus:AuthorizeStatus)=>{
      if (authorizeStatus.status) {
        this.service.setFavouriteOfferSelected(fo);
        this.router.navigate(['/home/favorite-hotel-authorization-resume']);
      } else {
        this.toastr.error(authorizeStatus.description);
      }
    }).catch((res:HttpErrorResponse) => {
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }

  onConfirmPackage(fo:FavouriteOffer){

    var authoReq:PackageAuthorizeRequest = new PackageAuthorizeRequest();
    authoReq.quantityPeople = fo.quantityCapacity;
    authoReq.portalUser = this.authService.getUser();

    this.poService.authorizeReservation(fo.id, authoReq)
    .then((authorizeStatus:AuthorizeStatus)=>{
      if (authorizeStatus.status) {
        this.service.setFavouriteOfferSelected(fo);
        this.router.navigate(['home']);
      } else {
        this.toastr.error(authorizeStatus.description);
      }
    }).catch((res:HttpErrorResponse) => {
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }

  isHotel(type:string){
    return Constant.HOTEL == type;
  }
}
