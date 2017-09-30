import { ToastrService } from 'ngx-toastr';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';

import { MdDialog } from '@angular/material';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';
import { HotelOfferService } from '../../services/hotel-offer.service';
import { HotelOffer } from '../../models/hotel-offer.model';
import { HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-hotel-offer-list-result',
  templateUrl: './hotel-offer-list-result.component.html',
  styleUrls: ['./hotel-offer-list-result.component.css']
})
export class HotelOfferListResultComponent implements OnInit, OnDestroy {

  @Input()hotelOffers:HotelOfferHeader[];

  @Input()sortDirection:string;
  @Input()sortField:string;

  private detailDialogSub:Subscription;

  constructor(
    private toastr: ToastrService,
    private hotelOfferService: HotelOfferService,
    private dialog: MdDialog) { }

  ngOnInit() {}

  ngOnDestroy(){
    if (this.detailDialogSub)
      this.detailDialogSub.unsubscribe();
  }

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
}
