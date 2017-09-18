import { Component, OnInit, Input } from '@angular/core';

import { MdDialog } from '@angular/material';

import { Hotel } from '../../models/hotel.model';

import { HotelDetailComponent } from '../hotel-detail/hotel-detail.component';

@Component({
  selector: 'app-hotel-list-result',
  templateUrl: './hotel-list-result.component.html',
  styleUrls: ['./hotel-list-result.component.css']
})
export class HotelListResultComponent implements OnInit {

  @Input()hotels:Hotel[];

  constructor(private dialog: MdDialog) { }

  ngOnInit() {
  }

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
}
