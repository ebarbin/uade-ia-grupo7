import { Component, Inject, OnInit } from '@angular/core';
import {MdDialogRef, MD_DIALOG_DATA} from '@angular/material';

import { HotelOffer } from '../../models/hotel-offer.model';

@Component({
  selector: 'app-hotel-offer-detail',
  templateUrl: './hotel-offer-detail.component.html',
  styleUrls: ['./hotel-offer-detail.component.css']
})
export class HotelOfferDetailComponent implements OnInit {

  constructor(public dialogRef: MdDialogRef<HotelOfferDetailComponent>,
    @Inject(MD_DIALOG_DATA) public hotelOffer: HotelOffer) { 
      console.log(hotelOffer);
    }

  ngOnInit() {
  }

}
