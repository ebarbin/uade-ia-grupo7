import { Component, OnInit, Input } from '@angular/core';

import { MdDialog } from '@angular/material';

import { HotelOffer } from '../../models/hotel-offer.model';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';

@Component({
  selector: 'app-hotel-offer-list-result',
  templateUrl: './hotel-offer-list-result.component.html',
  styleUrls: ['./hotel-offer-list-result.component.css']
})
export class HotelOfferListResultComponent implements OnInit {

  @Input()hotelOffers:HotelOffer[];

  constructor(private dialog: MdDialog) { }

  ngOnInit() {
  }

  onDetail(hotelOffer:HotelOffer){
    const dialogRef = this.dialog.open(HotelOfferDetailComponent, {
      height: '600px',
      width: '900px',
      data: hotelOffer
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
