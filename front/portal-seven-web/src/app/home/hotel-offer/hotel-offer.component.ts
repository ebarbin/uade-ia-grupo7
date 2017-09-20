import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm, FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';

import { HotelService } from './services/hotel.service';

import { HotelOfferRequest } from './models/hotel-offer-request.model';
import { HotelOffer } from './models/hotel-offer.model';

@Component({
  selector: 'app-hotel-offer',
  templateUrl: './hotel-offer.component.html',
  styleUrls: ['./hotel-offer.component.css']
})
export class HotelOfferComponent implements OnInit {

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

  results:HotelOffer[] = [];
  onSearch(hotelOfferRequest: HotelOfferRequest){
    //BUSCAR LOS HOTELES/OFERTAS??
    console.log(hotelOfferRequest);

    this.results = [
      new HotelOffer(1, 'Hotel 1', null),
      new HotelOffer(2, 'Hotel 2', null),
      new HotelOffer(3, 'Hotel 3', null),
      new HotelOffer(4, 'Hotel 4', null),
      new HotelOffer(5, 'Hotel 5', null),
      new HotelOffer(6, 'Hotel 6', null),
      new HotelOffer(7, 'Hotel 7', null)
    ];
  }
  
}
