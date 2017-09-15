import { Component, OnInit, OnDestroy,Input  } from '@angular/core';

import { Subscription } from 'rxjs/Subscription'

import { MdDialog } from '@angular/material';

import { HotelDetailComponent } from '../hotel-detail/hotel-detail.component';
import { Hotel } from '../../models/hotel.model';
import { CustomDatasource } from '../../../../shared/models/custom-datasouce';
import { HotelService } from '../../services/hotel.service';

@Component({
  selector: 'app-hotel-grid-result',
  templateUrl: './hotel-grid-result.component.html',
  styleUrls: ['./hotel-grid-result.component.css']
})
export class HotelGridResultComponent implements OnInit, OnDestroy {

  dataSource: CustomDatasource;

  //resultsSubs: Subscription;

  displayedColumns = ['id', 'name', 'image', 'other', 'action'];

  constructor(
    private hotelService: HotelService, 
    private dialog: MdDialog) { }

  onDetail(element){
    const dialogRef = this.dialog.open(HotelDetailComponent, {
      height: '600px',
      width: '900px',
      data: element
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
