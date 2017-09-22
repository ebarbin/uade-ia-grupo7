import { PackageOffer } from './../../models/package-offer.model';
import { Component, Input, OnInit } from '@angular/core';
import { MdDialog } from '@angular/material';
import { HotelOffer } from '../../../hotel-offer/models/hotel-offer.model';
import {
    PackageOfferDetailComponent
} from '../package-offer-detail/package-offer-detail.component';

@Component({
  selector: 'app-package-offer-card-result',
  templateUrl: './package-offer-card-result.component.html',
  styleUrls: ['./package-offer-card-result.component.css']
})
export class PackageOfferCardResultComponent implements OnInit {

  @Input()packageOffers:PackageOffer[];
  
    constructor(private dialog: MdDialog) { }
  
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
  
    ngOnInit() {}

}
