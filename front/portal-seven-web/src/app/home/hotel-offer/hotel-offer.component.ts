import { Component, OnInit } from '@angular/core';
import { HotelOfferService } from './services/hotel-offer.service';

@Component({
  selector: 'app-hotel-offer',
  templateUrl: './hotel-offer.component.html',
  styleUrls: ['./hotel-offer.component.css']
})
export class HotelOfferComponent implements OnInit {

  constructor(
    private hotelOfferService:HotelOfferService) { }

  ngOnInit() {
    this.hotelOfferService.reset();
  }
}
