import { ToastrService } from 'ngx-toastr';
import { Component } from '@angular/core';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { MdDialog } from '@angular/material';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';
import { HotelOffer } from '../../models/hotel-offer.model';
import { HotelOfferService } from '../../services/hotel-offer.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-hotel-offer-card-result',
  templateUrl: './hotel-offer-card-result.component.html',
  styleUrls: ['./hotel-offer-card-result.component.css']
})
export class HotelOfferCardResultComponent {

  constructor(
    public srv: HotelOfferService,
    private toastr:ToastrService,
    private dialog: MdDialog) { }

  onDetail(hotelOfferHeader:HotelOfferHeader){
    this.srv.getDetail(hotelOfferHeader).then((hotelOffer:HotelOffer)=>{
      if (hotelOffer)
        this.dialog.open(HotelOfferDetailComponent);
    }).catch((res:HttpErrorResponse)=>{
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }
}
