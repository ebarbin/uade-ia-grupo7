import { PackageOfferHeader } from './../../models/package-offer-header.model';
import { Component, Input, OnInit } from '@angular/core';
import { MdDialog } from '@angular/material';
import {
    PackageOfferDetailComponent
} from '../package-offer-detail/package-offer-detail.component';

@Component({
  selector: 'app-package-offer-list-result',
  templateUrl: './package-offer-list-result.component.html',
  styleUrls: ['./package-offer-list-result.component.css']
})
export class PackageOfferListResultComponent implements OnInit {

  @Input()packageOffers:PackageOfferHeader[];
  
    constructor(private dialog: MdDialog) { }
  
    ngOnInit() {
    }
  
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

}
