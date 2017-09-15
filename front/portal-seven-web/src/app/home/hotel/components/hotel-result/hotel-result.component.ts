import { Component, OnInit, OnDestroy } from '@angular/core';

import { Subscription } from 'rxjs/Subscription'

import { MdDialog } from '@angular/material';

import { HotelDetailComponent } from '../hotel-detail/hotel-detail.component';
import { Hotel } from '../../models/hotel.model';
import { CustomDatasource } from '../../../../shared/models/custom-datasouce';
import { HotelService } from '../../services/hotel.service';

@Component({
  selector: 'app-hotel-result',
  templateUrl: './hotel-result.component.html',
  styleUrls: ['./hotel-result.component.css']
})
export class HotelResultComponent implements OnInit, OnDestroy {

  dataSource: CustomDatasource;

  resultsSubs: Subscription;

  displayedColumns = ['id', 'name', 'image', 'action'];

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

  ngOnDestroy(){
    this.resultsSubs.unsubscribe();
  }
  
  ngOnInit() {
    this.resultsSubs = this.hotelService.hotelResults.subscribe((hotels:Hotel[])=>{
      this.dataSource = new CustomDatasource(hotels);
    });
  }

}
