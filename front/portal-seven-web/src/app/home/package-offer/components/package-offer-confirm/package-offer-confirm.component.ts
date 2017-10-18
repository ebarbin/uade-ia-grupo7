import { HttpErrorResponse } from '@angular/common/http';
import { AuthorizeStatus } from './../../../../shared/models/authorize-status.model';
import { PackageOfferDetailComponent } from './../package-offer-detail/package-offer-detail.component';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { MdDialog, MdDialogRef } from '@angular/material';
import { PackageOfferService } from './../../services/package-offer.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-package-offer-confirm',
  templateUrl: './package-offer-confirm.component.html',
  styleUrls: ['./package-offer-confirm.component.css']
})
export class PackageOfferConfirmComponent {

  constructor(
    public srv: PackageOfferService,
    private dialog: MdDialog,
    private router: Router,
    private toastr: ToastrService,
    private dialogRef: MdDialogRef<PackageOfferConfirmComponent>) { 
  }

  getTotalPrice(unitaryPrice:number){
    return unitaryPrice * this.srv.getFilter().quantityPeople;
  }

  onCancel(){
    this.dialogRef.close();
    this.dialog.open(PackageOfferDetailComponent);
  }

  onConfirm(){
    this.srv.authorizeReservation()
    .then((authorizeStatus:AuthorizeStatus)=>{
      if (authorizeStatus.status) {
        this.dialogRef.close();
        this.router.navigate(['home/package-authorization-resume']);
      } else {
        this.toastr.error(authorizeStatus.description);
        this.onCancel();
      }
    }).catch((res:HttpErrorResponse) => {
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }
}
