import { Component, OnInit, OnDestroy,Input  } from '@angular/core';

import { Subscription } from 'rxjs/Subscription'

import { MdDialog } from '@angular/material';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { CustomDatasource } from '../../../../shared/models/custom-datasouce';

@Component({
  selector: 'app-hotel-offer-grid-result',
  templateUrl: './hotel-offer-grid-result.component.html',
  styleUrls: ['./hotel-offer-grid-result.component.css']
})
export class HotelOfferGridResultComponent implements OnInit, OnDestroy {

  dataSource: CustomDatasource;

  displayedColumns = [ 'name', 'description', 'services', 'price', 'action'];

  constructor(
    private dialog: MdDialog) { }

  onDetail(hotel:HotelOfferHeader){
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
  
  @Input()hotelOffers:HotelOfferHeader[];
  
  ngOnInit() {
    this.dataSource = new CustomDatasource(this.hotelOffers);
  }

}
