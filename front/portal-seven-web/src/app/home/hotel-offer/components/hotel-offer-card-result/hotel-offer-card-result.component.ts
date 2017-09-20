import { Component, OnInit, Input } from '@angular/core';

import { HotelOffer } from '../../models/hotel-offer.model';
import { MdDialog } from '@angular/material';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';

@Component({
  selector: 'app-hotel-offer-card-result',
  templateUrl: './hotel-offer-card-result.component.html',
  styleUrls: ['./hotel-offer-card-result.component.css']
})
export class HotelOfferCardResultComponent implements OnInit {

  @Input()hotelOffers:HotelOffer[];

  constructor(private dialog: MdDialog) { }

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

  ngOnInit() {}

}
