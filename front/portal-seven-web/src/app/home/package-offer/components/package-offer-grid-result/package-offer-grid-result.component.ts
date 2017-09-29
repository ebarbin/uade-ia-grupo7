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

  @Input()packageOffers:PackageOfferHeader[];
  private resultsChangeSub:Subscription;

  public dataSource: CustomDatasource;
  public displayedColumns = ['id', 'name', 'image', 'other', 'action'];

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
      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      });
    }).catch((res:HttpErrorResponse)=>{
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }

  ngOnInit() {
    this.dataSource = new CustomDatasource(this.packageOffers);
    this.resultsChangeSub = this.packageOfferService.resultsChanged.subscribe((data:PackageOfferHeader[])=>{
      this.dataSource = new CustomDatasource(data);
    });
  }

  ngOnDestroy(){
    this.resultsChangeSub.unsubscribe();
  }
}
