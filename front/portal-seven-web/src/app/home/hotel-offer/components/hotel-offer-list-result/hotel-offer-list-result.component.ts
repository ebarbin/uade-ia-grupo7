import { Component, OnInit, Input } from '@angular/core';

import { MdDialog } from '@angular/material';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';
import { HotelOfferService } from '../../services/hotel-offer.service';
import { HotelOffer } from '../../models/hotel-offer.model';
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandlerService } from '../../../../shared/services/error-handler.service';

@Component({
  selector: 'app-hotel-offer-list-result',
  templateUrl: './hotel-offer-list-result.component.html',
  styleUrls: ['./hotel-offer-list-result.component.css']
})
export class HotelOfferListResultComponent implements OnInit {

  @Input()hotelOffers:HotelOfferHeader[];

  constructor(
    private errorHandlerService: ErrorHandlerService,
    private hotelOfferService: HotelOfferService,
    private dialog: MdDialog) { }

  ngOnInit() {
  }

  onDetail(hotelOfferHeader:HotelOfferHeader){
    this.hotelOfferService.getDetail(hotelOfferHeader).then((hotelOffer:HotelOffer)=>{
      const dialogRef = this.dialog.open(HotelOfferDetailComponent, {
        height: '600px',
        width: '900px',
        data: hotelOffer
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      });
    }).catch((res:HttpErrorResponse)=>{
      this.errorHandlerService.set(res);
    });
  }
}
