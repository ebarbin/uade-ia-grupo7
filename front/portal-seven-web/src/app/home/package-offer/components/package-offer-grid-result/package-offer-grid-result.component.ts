import { PackageOfferHeader } from './../../models/package-offer-header.model';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MdDialog, MdSort } from '@angular/material';
import { CustomDatasource } from '../../../../shared/models/custom-datasouce';
import {
    PackageOfferDetailComponent
} from '../package-offer-detail/package-offer-detail.component';

@Component({
  selector: 'app-package-offer-grid-result',
  templateUrl: './package-offer-grid-result.component.html',
  styleUrls: ['./package-offer-grid-result.component.css']
})
export class PackageOfferGridResultComponent implements OnInit {

  @ViewChild(MdSort) sort: MdSort;

  dataSource: CustomDatasource;
  
  displayedColumns = ['id', 'name', 'image', 'other', 'action'];

  constructor(
    private dialog: MdDialog) { }

  onDetail(packageOffer:PackageOfferHeader){
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
  
  @Input()packageOffers:PackageOfferHeader[];
  
  ngOnInit() {
    this.dataSource = new CustomDatasource(this.packageOffers);
  }

}
