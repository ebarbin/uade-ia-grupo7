import { ToastrService } from 'ngx-toastr';
import { Component, OnInit, OnDestroy, Input } from '@angular/core';

import { Subscription } from 'rxjs/Subscription'

import { MdDialog } from '@angular/material';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { CustomDatasource } from '../../../../shared/models/custom-datasouce';
import { HotelOfferService } from '../../services/hotel-offer.service';
import { HotelOffer } from '../../models/hotel-offer.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-hotel-offer-grid-result',
  templateUrl: './hotel-offer-grid-result.component.html',
  styleUrls: ['./hotel-offer-grid-result.component.css']
})
export class HotelOfferGridResultComponent implements OnInit, OnDestroy {

  private resultsChangeSub:Subscription;

  public dataSource: CustomDatasource;
  public displayedColumns = [ 'name', 'description', 'services', 'price', 'roomCapacity', 
    'offerStart', 'offerEnd', 'detail'];

  constructor(
    public hotelOfferService: HotelOfferService,
    private toastr: ToastrService,
    private dialog: MdDialog) { }

  onDetail(hotelOfferHeader:HotelOfferHeader){
    this.hotelOfferService.getDetail(hotelOfferHeader).then((hotelOffer:HotelOffer) =>{
      if (hotelOffer)
        this.dialog.open(HotelOfferDetailComponent,{
          width: '850px'
        });
    }).catch((res:HttpErrorResponse)=>{
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }

  ngOnInit() {
    this.dataSource = new CustomDatasource(this.hotelOfferService.getResults());
    this.resultsChangeSub = this.hotelOfferService.getResultsChanged()
      .subscribe((data:HotelOfferHeader[])=>{
      this.dataSource = new CustomDatasource(data);
    });
  }

  ngOnDestroy(){
    this.resultsChangeSub.unsubscribe();
  }
}
