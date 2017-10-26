import { AuthService } from './../../../../auth/services/auth.service';
import { HotelAuthorizeRequest } from './../../../../shared/models/hotel-authorize-request.model';
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
    private toastr: ToastrService,
    private authService: AuthService,
    private dialog: MdDialog,
    private router: Router,
    private dialogRef: MdDialogRef<HotelOfferConfirmComponent>) { 
  }

  onCancel(){
    this.dialogRef.close();
    this.dialog.open(HotelOfferDetailComponent,{
      width: '850px'
    });
  }

  onConfirm(){
    var req = new HotelAuthorizeRequest(this.srv.getFilterRequest().roomQuantity, this.srv.getFilterRequest().fromDate, this.srv.getFilterRequest().toDate);
    this.srv.authorizeReservation(this.srv.getSelected().id, req)
      .then((authorizeStatus:AuthorizeStatus)=>{
        if (authorizeStatus.status) {
          this.dialogRef.close();
          this.router.navigate(['home/hotel-authorization-resume']);
        } else {
          this.toastr.error(authorizeStatus.description);
        }
      }).catch((res:HttpErrorResponse) => {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
  }

  canConfirm(){
    return !this.authService.isAdmin();
  }
}
