import { ToastrService } from 'ngx-toastr';
import { Component, OnInit, Input } from '@angular/core';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { MdDialog } from '@angular/material';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';
import { HotelOffer } from '../../models/hotel-offer.model';
import { HotelOfferService } from '../../services/hotel-offer.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-hotel-offer-card-result',
  templateUrl: './hotel-offer-card-result.component.html',
  styleUrls: ['./hotel-offer-card-result.component.css']
})
export class HotelOfferCardResultComponent implements OnInit {

  @Input()hotelOffers:HotelOfferHeader[] = [];

  private detailDialogSub:Subscription;

  constructor(
    private toastr:ToastrService,
    private hotelOfferService: HotelOfferService,
    private dialog: MdDialog) { }

  onDetail(hotelOfferHeader:HotelOfferHeader){
    this.hotelOfferService.getDetail(hotelOfferHeader).then((hotelOffer:HotelOffer)=>{
      const dialogRef = this.dialog.open(HotelOfferDetailComponent, {
        height: '600px',
        width: '900px',
        data: hotelOffer
      });
  
     this.detailDialogSub = dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      });
    }).catch((res:HttpErrorResponse)=>{
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }

  ngOnInit() {}

  ngOnDestroy(){
    if (this.detailDialogSub)
      this.detailDialogSub.unsubscribe();
  }
}
