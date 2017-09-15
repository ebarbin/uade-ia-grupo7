import { Component, OnInit, Input } from '@angular/core';

import { Hotel } from '../../models/hotel.model';
import { MdDialog } from '@angular/material';

import { HotelDetailComponent } from '../hotel-detail/hotel-detail.component';

@Component({
  selector: 'app-hotel-card-result',
  templateUrl: './hotel-card-result.component.html',
  styleUrls: ['./hotel-card-result.component.css']
})
export class HotelCardResultComponent implements OnInit {

  @Input()hotels:Hotel[];

  constructor(private dialog: MdDialog) { }

  onDetail(hotel){
    const dialogRef = this.dialog.open(HotelDetailComponent, {
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
