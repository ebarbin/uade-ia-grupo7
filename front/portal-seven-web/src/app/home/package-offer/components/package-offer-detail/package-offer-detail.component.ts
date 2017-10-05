import { PackageOfferHeader } from './../../models/package-offer-header.model';
import { MD_DIALOG_DATA, MdDialogRef, MdDialog } from '@angular/material';
import { Component, Inject } from '@angular/core';
import { PackageOfferService } from '../../services/package-offer.service';
import { ToastrService } from 'ngx-toastr';
import { PackageOfferConfirmComponent } from '../package-offer-confirm/package-offer-confirm.component';

@Component({
  selector: 'app-package-offer-detail',
  templateUrl: './package-offer-detail.component.html',
  styleUrls: ['./package-offer-detail.component.css']
})
export class PackageOfferDetailComponent {

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
}
