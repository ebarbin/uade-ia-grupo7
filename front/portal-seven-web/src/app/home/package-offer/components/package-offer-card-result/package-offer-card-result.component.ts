import { Subscription } from 'rxjs/Subscription';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { PackageOffer } from './../../models/package-offer.model';
import { PackageOfferService } from './../../services/package-offer.service';
import { PackageOfferHeader } from './../../models/package-offer-header.model';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { MdDialog } from '@angular/material';
import {
    PackageOfferDetailComponent
} from '../package-offer-detail/package-offer-detail.component';

@Component({
  selector: 'app-package-offer-card-result',
  templateUrl: './package-offer-card-result.component.html',
  styleUrls: ['./package-offer-card-result.component.css']
})
export class PackageOfferCardResultComponent implements OnInit, OnDestroy {

    packageOffers:PackageOfferHeader[];
  
    private detailDialogSub:Subscription;
    private packageOffersSub:Subscription;

    constructor(
      private toastr: ToastrService,
      private packageOfferService: PackageOfferService,
      private dialog: MdDialog) { }
  
    onDetail(packageOfferHeader:PackageOfferHeader){
      this.packageOfferService.getDetail(packageOfferHeader).then((packageOffer:PackageOffer)=>{
        const dialogRef = this.dialog.open(PackageOfferDetailComponent, {
          height: '600px',
          width: '900px',
          data: packageOffer
        });
        this.detailDialogSub = dialogRef.afterClosed().subscribe(result => {
          console.log(result);
        });
      }).catch((res:HttpErrorResponse)=>{
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
    }
  
    ngOnInit() {
      this.packageOffers = this.packageOfferService.getResults();
      this.packageOffersSub = this.packageOfferService.resultsChanged
        .subscribe((results:PackageOfferHeader[])=>{
        this.packageOffers = results;
      });
    }

    ngOnDestroy(){
      this.packageOffersSub.unsubscribe();
      if (this.detailDialogSub) this.detailDialogSub.unsubscribe();
    }
}
