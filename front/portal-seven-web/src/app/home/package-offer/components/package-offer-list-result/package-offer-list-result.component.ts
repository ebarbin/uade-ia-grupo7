import { PackageOffer } from './../../models/package-offer.model';
import { Component, Input, OnInit } from '@angular/core';
import { MdDialog } from '@angular/material';
import { HotelOffer } from '../../../hotel-offer/models/hotel-offer.model';
import {
    PackageOfferDetailComponent
} from '../package-offer-detail/package-offer-detail.component';

@Component({
  selector: 'app-package-offer-list-result',
  templateUrl: './package-offer-list-result.component.html',
  styleUrls: ['./package-offer-list-result.component.css']
})
export class PackageOfferListResultComponent implements OnInit {

  @Input()packageOffers:PackageOffer[];
  
    constructor(private dialog: MdDialog) { }
  
    ngOnInit() {
    }
  
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

}
