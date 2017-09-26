import { Component, Inject, OnInit } from '@angular/core';
import {MdDialogRef, MD_DIALOG_DATA} from '@angular/material';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { HotelOffer } from '../../models/hotel-offer.model';

@Component({
  selector: 'app-hotel-offer-detail',
  templateUrl: './hotel-offer-detail.component.html',
  styleUrls: ['./hotel-offer-detail.component.css']
})
export class HotelOfferDetailComponent implements OnInit {

  hotelOffer: HotelOffer;

  constructor(
    private dialogRef: MdDialogRef<HotelOfferDetailComponent>,
    @Inject(MD_DIALOG_DATA) private data: { hotelOffer: HotelOffer }) {
    }

    onClose(){
      this.dialogRef.close("Close");
    }

    onReserve(){
      //TODO MAKE RESERVATION
      this.dialogRef.close("Reserve");
    }

    ngOnInit() {
      this.hotelOffer = this.data.hotelOffer;
    }

}
