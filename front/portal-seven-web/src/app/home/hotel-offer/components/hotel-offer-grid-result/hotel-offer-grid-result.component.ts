import { Component, OnInit, OnDestroy,Input  } from '@angular/core';

import { Subscription } from 'rxjs/Subscription'

import { MdDialog } from '@angular/material';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';

import { Hotel } from '../../models/hotel.model';
import { CustomDatasource } from '../../../../shared/models/custom-datasouce';
import { HotelService } from '../../services/hotel.service';

@Component({
  selector: 'app-hotel-offer-grid-result',
  templateUrl: './hotel-offer-grid-result.component.html',
  styleUrls: ['./hotel-offer-grid-result.component.css']
})
export class HotelOfferGridResultComponent implements OnInit, OnDestroy {

  dataSource: CustomDatasource;

  //resultsSubs: Subscription;

  displayedColumns = ['id', 'name', 'image', 'other', 'action'];

  constructor(
    private hotelService: HotelService, 
    private dialog: MdDialog) { }

  onDetail(hotel){
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
  
  @Input()hotels:Hotel[];
  
  ngOnInit() {
    this.dataSource = new CustomDatasource(this.hotels);
  }

}
