import { Component, OnInit, OnDestroy,Input  } from '@angular/core';

import { Subscription } from 'rxjs/Subscription'

import { MdDialog } from '@angular/material';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { CustomDatasource } from '../../../../shared/models/custom-datasouce';
import { HotelOfferService } from '../../services/hotel-offer.service';
import { HotelOffer } from '../../models/hotel-offer.model';
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandlerService } from '../../../../shared/services/error-handler.service';

@Component({
  selector: 'app-hotel-offer-grid-result',
  templateUrl: './hotel-offer-grid-result.component.html',
  styleUrls: ['./hotel-offer-grid-result.component.css']
})
export class HotelOfferGridResultComponent implements OnInit, OnDestroy {

  dataSource: CustomDatasource;

  displayedColumns = [ 'name', 'description', 'services', 'price', 'action'];

  constructor(
    private errorHandlerService:ErrorHandlerService,
    private hotelOfferService: HotelOfferService,
    private dialog: MdDialog) { }

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

  ngOnDestroy(){}
  
  @Input()hotelOffers:HotelOfferHeader[];
  
  ngOnInit() {
    this.dataSource = new CustomDatasource(this.hotelOffers);
  }

}
