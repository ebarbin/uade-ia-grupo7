import { AuthService } from './../../../../auth/services/auth.service';
import { Subscription } from 'rxjs/Subscription';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { PackageOffer } from './../../models/package-offer.model';
import { PackageOfferService } from './../../services/package-offer.service';
import { PackageOfferHeader } from './../../models/package-offer-header.model';
import { Component } from '@angular/core';
import { MdDialog } from '@angular/material';
import {
    PackageOfferDetailComponent
} from '../package-offer-detail/package-offer-detail.component';

@Component({
  selector: 'app-package-offer-card-result',
  templateUrl: './package-offer-card-result.component.html',
  styleUrls: ['./package-offer-card-result.component.css']
})
export class PackageOfferCardResultComponent {

    constructor(
      public poService: PackageOfferService,
      private authService: AuthService,
      private toastr: ToastrService,
      private dialog: MdDialog) { }
  
    onDetail(packageOfferHeader:PackageOfferHeader){
      this.poService.getDetail(packageOfferHeader).then((packageOffer:PackageOffer)=>{
        if(packageOffer)
          this.dialog.open(PackageOfferDetailComponent,{
            width: '850px'
          });
      }).catch((res:HttpErrorResponse)=>{
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
    }

    getTotalPrice(unitaryPrice:number){
      return unitaryPrice * this.poService.getFilter().quantityPeople;
    }

    showFavouriteIcon(){
      return this.authService.getUser().id != null;
    }
}
