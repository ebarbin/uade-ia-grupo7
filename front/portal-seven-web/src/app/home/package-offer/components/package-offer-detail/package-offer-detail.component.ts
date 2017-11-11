import { HttpErrorResponse } from '@angular/common/http';
import { PackageOfferHeader } from './../../models/package-offer-header.model';
import { MD_DIALOG_DATA, MdDialogRef, MdDialog } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { PackageOfferService } from '../../services/package-offer.service';
import { ToastrService } from 'ngx-toastr';
import { PackageOfferConfirmComponent } from '../package-offer-confirm/package-offer-confirm.component';

@Component({
  selector: 'app-package-offer-detail',
  templateUrl: './package-offer-detail.component.html',
  styleUrls: ['./package-offer-detail.component.css']
})
export class PackageOfferDetailComponent implements OnInit{ 

  otherPackages:PackageOfferHeader[] = [];

  constructor(
    public srv: PackageOfferService,
    private dialog: MdDialog,
    private toastr: ToastrService,
    private dialogRef: MdDialogRef<PackageOfferDetailComponent>) {}

    onClose(){
      this.dialogRef.close();
    }

    onReserve(){
      this.dialogRef.close();
      this.dialog.open(PackageOfferConfirmComponent, {
        width: '300px'
      });
    }

    ngOnInit(){
      this.srv.searchOtherPackages().then((packagaeOffers:PackageOfferHeader[])=>{
        this.otherPackages = packagaeOffers;
      }).catch((res:HttpErrorResponse) => {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
    }

}
