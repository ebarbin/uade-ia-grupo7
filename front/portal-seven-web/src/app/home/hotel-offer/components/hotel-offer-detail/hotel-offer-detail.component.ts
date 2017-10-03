import { HttpErrorResponse } from '@angular/common/http';
import { HotelOfferOtherRoomsRequest } from './../../models/hotel-offer-other-room-request.model';
import { HotelOfferService } from './../../services/hotel-offer.service';
import { Room } from './../../models/room.model';
import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { MdDialogRef, MD_DIALOG_DATA, MdDialog, MdTabChangeEvent } from '@angular/material';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { HotelOffer } from '../../models/hotel-offer.model';
import { ConfirmComponent } from '../../../../shared/components/confirm/confirm.component';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs/Subscription';
import { HotelOfferConfirmComponent } from '../hotel-offer-confirm/hotel-offer-confirm.component';

@Component({
  selector: 'app-hotel-offer-detail',
  templateUrl: './hotel-offer-detail.component.html',
  styleUrls: ['./hotel-offer-detail.component.css']
})
export class HotelOfferDetailComponent implements OnInit, OnDestroy {

  hotelOffer: HotelOffer;
  otherRooms: Room[] = [];
  private confirmSub:Subscription;

  constructor(
    private dialog: MdDialog,
    private toastr: ToastrService,
    private hotelOfferServive: HotelOfferService,
    private dialogRef: MdDialogRef<HotelOfferDetailComponent>,
    @Inject(MD_DIALOG_DATA) private data: HotelOffer) {}

    onClose(){
      this.dialogRef.close();
    }

    getOtherRooms(event:MdTabChangeEvent){
      if (event.index == 1 && this.otherRooms.length == 0) {
        var req:HotelOfferOtherRoomsRequest = new HotelOfferOtherRoomsRequest(this.hotelOffer);
        this.hotelOfferServive.searchOtherRooms(req).then((rooms:Room[])=>{
          this.otherRooms = rooms;
        }).catch((res:HttpErrorResponse)=>{
          this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
        });
      }
    }

    onReserve(){
      const confirmDialogRef = this.dialog.open(HotelOfferConfirmComponent, {
        width: '300px',
        data: this.hotelOffer
      });
  
      this.confirmSub = confirmDialogRef.afterClosed().subscribe(confirm => {
        if (confirm) {
          //TODO Make reservation!
          this.dialogRef.close();
        }
      });
    }

    ngOnInit() {
      this.hotelOffer = this.data;
    }

    ngOnDestroy(){
      if (this.confirmSub)
        this.confirmSub.unsubscribe();
    }
}
