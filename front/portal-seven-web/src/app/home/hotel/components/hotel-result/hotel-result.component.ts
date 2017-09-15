import { Component, OnInit } from '@angular/core';

import { MdDialog } from '@angular/material';

import { ResultSearchHotelDS } from '../../models/results-search-hotel-ds';
import { HotelDetailComponent } from '../hotel-detail/hotel-detail.component';

@Component({
  selector: 'app-hotel-result',
  templateUrl: './hotel-result.component.html',
  styleUrls: ['./hotel-result.component.css']
})
export class HotelResultComponent implements OnInit {

  dataSource = new ResultSearchHotelDS();
  displayedColumns = ['position', 'name', 'weight', 'symbol'];

  constructor(public dialog: MdDialog) { }

  openDialog(element){
    const dialogRef = this.dialog.open(HotelDetailComponent, {
      height: '600px',
      width: '900px',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  ngOnInit() {
  }

  test(element){
    console.log(element);
  }

}
