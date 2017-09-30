import { ToastrService } from 'ngx-toastr';
import { Component, OnInit, OnDestroy, Input } from '@angular/core';

import { Subscription } from 'rxjs/Subscription'

import { MdDialog, Sort } from '@angular/material';

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

  @Input()hotelOffers:HotelOfferHeader[];

  private resultsChangeSub:Subscription;
  private detailDialogSub:Subscription;

  public dataSource: CustomDatasource;
  public displayedColumns = [ 'name', 'description', 'services', 'price', 'roomCapacity', 'offerStart', 'offerEnd', 'action'];

  constructor(
    private toastr: ToastrService,
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
        console.log("close detail");
      });
    }).catch((res:HttpErrorResponse)=>{
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }

  ngOnInit() {
    this.dataSource = new CustomDatasource(this.hotelOffers);
    this.resultsChangeSub = this.hotelOfferService.resultsChanged.subscribe((data:HotelOfferHeader[])=>{
      this.dataSource = new CustomDatasource(data);
    });
  }

  ngOnDestroy(){
    this.resultsChangeSub.unsubscribe();

    if(this.detailDialogSub)
      this.detailDialogSub.unsubscribe();
  }

  sortData(sort: Sort) {
    this.dataSource.data = this.dataSource.data.sort((a, b) => {
      let isAsc = sort.direction == 'asc';
      return compare(a[sort.active], b[sort.active], isAsc);
    });
    this.hotelOfferService.resultsChanged.next(this.dataSource.data);
  }
}

function compare(a, b, isAsc) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
