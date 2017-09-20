import { Component, OnInit, Input } from '@angular/core';

import { Hotel } from '../../models/hotel.model';
import { MdDialog } from '@angular/material';

import { HotelOfferDetailComponent } from '../hote-offerl-detail/hotel-offer-detail.component';

@Component({
  selector: 'app-hotel-offer-card-result',
  templateUrl: './hotel-offer-card-result.component.html',
  styleUrls: ['./hotel-offer-card-result.component.css']
})
export class HotelOfferCardResultComponent implements OnInit {

  @Input()hotels:Hotel[];

  constructor(private dialog: MdDialog) { }

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

  ngOnInit() {}

}
