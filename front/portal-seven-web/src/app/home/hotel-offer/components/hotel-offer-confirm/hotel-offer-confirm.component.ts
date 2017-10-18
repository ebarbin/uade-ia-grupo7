import { HotelOfferDetailComponent } from './../hotel-offer-detail/hotel-offer-detail.component';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthorizeStatus } from './../../../../shared/models/authorize-status.model';
import { HotelOfferService } from './../../services/hotel-offer.service';
import { Component, Inject } from '@angular/core';
import { MdDialogRef, MdDialog } from '@angular/material';
import { HotelOffer } from '../../models/hotel-offer.model';

@Component({
  selector: 'app-hotel-offer-confirm',
  templateUrl: './hotel-offer-confirm.component.html',
  styleUrls: ['./hotel-offer-confirm.component.css']
})
export class HotelOfferConfirmComponent {

  constructor(
    public srv: HotelOfferService,
    private dialog: MdDialog,
    private router: Router,
    private toastr: ToastrService,
    private dialogRef: MdDialogRef<HotelOfferConfirmComponent>) { 
  }

  onCancel(){
    this.dialogRef.close();
    this.dialog.open(HotelOfferDetailComponent);
  }

  onConfirm(){
    this.srv.authorizeReservation()
    .then((authorizeStatus:AuthorizeStatus)=>{
      if (authorizeStatus.status) {
        this.dialogRef.close();
        this.router.navigate(['home/hotel-authorization-resume']);
      } else {
        this.toastr.error(authorizeStatus.description);
        this.onCancel();
      }
    }).catch((res:HttpErrorResponse) => {
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }
}
