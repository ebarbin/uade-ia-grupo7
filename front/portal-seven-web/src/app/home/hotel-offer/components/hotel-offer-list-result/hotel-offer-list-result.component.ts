import { Component, OnInit, Input } from '@angular/core';

import { MdDialog } from '@angular/material';

import { Hotel } from '../../models/hotel.model';

import { HotelOfferDetailComponent } from '../hotel-offer-detail/hotel-offer-detail.component';

@Component({
  selector: 'app-hotel-offer-list-result',
  templateUrl: './hotel-offer-list-result.component.html',
  styleUrls: ['./hotel-offer-list-result.component.css']
})
export class HotelOfferListResultComponent implements OnInit {

  @Input()hotels:Hotel[];

  constructor(private dialog: MdDialog) { }

  ngOnInit() {
  }

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
}
