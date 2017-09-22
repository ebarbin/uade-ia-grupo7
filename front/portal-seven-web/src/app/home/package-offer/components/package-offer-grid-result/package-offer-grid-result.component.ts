import { PackageOffer } from './../../models/package-offer.model';
import { Component, Input, OnInit } from '@angular/core';
import { MdDialog } from '@angular/material';
import { CustomDatasource } from '../../../../shared/models/custom-datasouce';
import { HotelService } from '../../../hotel-offer/services/hotel.service';
import {
    PackageOfferDetailComponent
} from '../package-offer-detail/package-offer-detail.component';

@Component({
  selector: 'app-package-offer-grid-result',
  templateUrl: './package-offer-grid-result.component.html',
  styleUrls: ['./package-offer-grid-result.component.css']
})
export class PackageOfferGridResultComponent implements OnInit {

  dataSource: CustomDatasource;
  
  displayedColumns = ['id', 'name', 'image', 'other', 'action'];

  constructor(
    private hotelService: HotelService, 
    private dialog: MdDialog) { }

  onDetail(packageOffer:PackageOffer){
    const dialogRef = this.dialog.open(PackageOfferDetailComponent, {
      height: '600px',
      width: '900px',
      data: packageOffer
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  ngOnDestroy(){}
  
  @Input()packageOffers:PackageOffer[];
  
  ngOnInit() {
    this.dataSource = new CustomDatasource(this.packageOffers);
  }

}
