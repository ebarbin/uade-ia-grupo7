import { Component, OnInit, OnDestroy,Input  } from '@angular/core';

import { Subscription } from 'rxjs/Subscription'

import { MdDialog } from '@angular/material';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';

import { HotelOffer } from '../../models/hotel-offer.model';
import { CustomDatasource } from '../../../../shared/models/custom-datasouce';

@Component({
  selector: 'app-hotel-offer-grid-result',
  templateUrl: './hotel-offer-grid-result.component.html',
  styleUrls: ['./hotel-offer-grid-result.component.css']
})
export class HotelOfferGridResultComponent implements OnInit, OnDestroy {

  dataSource: CustomDatasource;

  displayedColumns = ['id', 'name', 'image', 'other', 'action'];

  constructor(
    private dialog: MdDialog) { }

  onDetail(hotel:HotelOffer){
    const dialogRef = this.dialog.open(HotelOfferDetailComponent, {
      height: '600px',
      width: '900px',
      data: hotel
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  ngOnDestroy(){}
  
  @Input()hotelOffers:HotelOffer[];
  
  ngOnInit() {
    this.dataSource = new CustomDatasource(this.hotelOffers);
  }

}
