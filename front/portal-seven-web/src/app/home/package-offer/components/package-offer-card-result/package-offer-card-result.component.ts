import { HttpErrorResponse } from '@angular/common/http';
import { PackageOffer } from './../../models/package-offer.model';
import { PackageOfferService } from './../../services/package-offer.service';
import { ErrorHandlerService } from './../../../../shared/services/error-handler.service';
import { PackageOfferHeader } from './../../models/package-offer-header.model';
import { Component, Input, OnInit } from '@angular/core';
import { MdDialog } from '@angular/material';
import {
    PackageOfferDetailComponent
} from '../package-offer-detail/package-offer-detail.component';

@Component({
  selector: 'app-package-offer-card-result',
  templateUrl: './package-offer-card-result.component.html',
  styleUrls: ['./package-offer-card-result.component.css']
})
export class PackageOfferCardResultComponent implements OnInit {

  @Input()packageOffers:PackageOfferHeader[];
  
    constructor(
      private errorHandlerService:ErrorHandlerService,
      private packageOfferService: PackageOfferService,
      private dialog: MdDialog) { }
  
    onDetail(packageOfferHeader:PackageOfferHeader){
      this.packageOfferService.getDetail(packageOfferHeader).then((packageOffer:PackageOffer)=>{
        const dialogRef = this.dialog.open(PackageOfferDetailComponent, {
          height: '600px',
          width: '900px',
          data: packageOffer
        });
        dialogRef.afterClosed().subscribe(result => {
          console.log(result);
        });
      }).catch((res:HttpErrorResponse)=>{
        this.errorHandlerService.set(res);
      });
    }
  
    ngOnInit() {}

}
