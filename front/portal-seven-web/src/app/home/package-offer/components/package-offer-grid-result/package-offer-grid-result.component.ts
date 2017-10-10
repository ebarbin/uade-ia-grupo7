import { ToastrService } from 'ngx-toastr';
import { PackageOffer } from './../../models/package-offer.model';
import { HttpErrorResponse } from '@angular/common/http';
import { PackageOfferService } from './../../services/package-offer.service';
import { Subscription } from 'rxjs/Subscription';
import { PackageOfferHeader } from './../../models/package-offer-header.model';
import { Component, Input, OnInit } from '@angular/core';
import { MdDialog } from '@angular/material';
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

  private resultsChangeSub:Subscription;

  public dataSource: CustomDatasource;
  public displayedColumns = ['description', 'services', 'price', 'totalPrice', 'offerStart', 'offerEnd', 'action'];

  constructor(
    public packageOfferService: PackageOfferService,
    private toastr: ToastrService,
    private dialog: MdDialog) { }

  onDetail(packageOfferHeader:PackageOfferHeader){
    
    this.packageOfferService.getDetail(packageOfferHeader).then((packageOffer:PackageOffer)=>{
      if(packageOffer)
        this.dialog.open(PackageOfferDetailComponent);
    }).catch((res:HttpErrorResponse)=>{
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }

  ngOnInit() {
    this.dataSource = new CustomDatasource(this.packageOfferService.getResults());
    this.resultsChangeSub = this.packageOfferService.getResultsChanged()
    .subscribe((data:PackageOfferHeader[])=>{
      this.dataSource = new CustomDatasource(data);
    });
  }

  ngOnDestroy(){
    this.resultsChangeSub.unsubscribe();
  }

  getTotalPrice(unitaryPrice:number){
    return unitaryPrice * this.packageOfferService.getFilter().quantityPeople;
  }
}
