import { PackageOffer } from './models/package-offer.model';
import { PackageOfferRequest } from './models/package-offer-request.model';
import { HotelService } from './../hotel-offer/services/hotel.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-package',
  templateUrl: './package-offer.component.html',
  styleUrls: ['./package-offer.component.css']
})
export class PackageOfferComponent implements OnInit {

  constructor(
    private hotelService:HotelService) { }

  ngOnInit() {}

  view:string = 'grid';

  onChangeView(view){
    this.view = view;
  }

  onReset(){
    this.results = [];
  }

  results:PackageOffer[] = [];
  onSearch(packageOfferRequest: PackageOfferRequest){

    //TODO Buscar la oferta de paquetes segun packageOfferRequest
    console.log(packageOfferRequest);

    this.results = [
      new PackageOffer(1, 'Package 1', null),
      new PackageOffer(2, 'Package 2', null),
      new PackageOffer(3, 'Package 3', null),
      new PackageOffer(4, 'Package 4', null),
      new PackageOffer(5, 'Package 5', null),
      new PackageOffer(6, 'Package 6', null),
      new PackageOffer(7, 'Package 7', null)
    ];
  }

}
