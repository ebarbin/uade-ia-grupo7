import { Component, OnInit, Inject } from '@angular/core';
import { MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { HotelOffer } from '../../models/hotel-offer.model';

@Component({
  selector: 'app-hotel-offer-confirm',
  templateUrl: './hotel-offer-confirm.component.html',
  styleUrls: ['./hotel-offer-confirm.component.css']
})
export class HotelOfferConfirmComponent implements OnInit {

  constructor(
    private dialogRef: MdDialogRef<HotelOfferConfirmComponent>,
    @Inject(MD_DIALOG_DATA) public hotelOffer: HotelOffer) { 
  }

  ngOnInit() {}

  onCancel(){
    this.dialogRef.close(false);
  }

  onConfirm(){
    this.dialogRef.close(true);
  }

}
