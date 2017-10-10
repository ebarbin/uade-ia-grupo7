import { AuthorizeStatus } from './../../../../shared/models/authorize-status.model';
import { HttpErrorResponse } from '@angular/common/http';
import { HotelOfferService } from './../../services/hotel-offer.service';
import { Room } from './../../models/room.model';
import { Component, Inject } from '@angular/core';
import { MdDialogRef, MdDialog, MdTabChangeEvent } from '@angular/material';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { HotelOffer } from '../../models/hotel-offer.model';
import { ToastrService } from 'ngx-toastr';
import { HotelOfferConfirmComponent } from '../hotel-offer-confirm/hotel-offer-confirm.component';

@Component({
  selector: 'app-hotel-offer-detail',
  templateUrl: './hotel-offer-detail.component.html',
  styleUrls: ['./hotel-offer-detail.component.css']
})
export class HotelOfferDetailComponent {

  otherRooms: Room[] = [];

  constructor(
    public srv: HotelOfferService,
    private dialog: MdDialog,
    private toastr: ToastrService,
    private dialogRef: MdDialogRef<HotelOfferDetailComponent>) {}

    onClose(){
      this.dialogRef.close();
    }

    getOtherRooms(event:MdTabChangeEvent){
      if (event.index == 1 && this.otherRooms.length == 0) {
        this.srv.searchOtherRooms().then((rooms:Room[])=>{
          this.otherRooms = rooms;
        }).catch((res:HttpErrorResponse)=>{
          this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
        });
      }
    }

    onReserve(){
      this.dialogRef.close();
      this.dialog.open(HotelOfferConfirmComponent, {
        width: '300px'
      });
    }
}
