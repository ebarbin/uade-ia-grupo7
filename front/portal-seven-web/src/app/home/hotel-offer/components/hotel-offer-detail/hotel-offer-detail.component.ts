import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { MdDialogRef, MD_DIALOG_DATA, MdDialog } from '@angular/material';

import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { HotelOffer } from '../../models/hotel-offer.model';
import { ConfirmComponent } from '../../../../shared/components/confirm/confirm.component';
import { DialogService } from '../../../../shared/services/confirm.service';
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

  private confirmSub:Subscription;

  constructor(
    private dialog: MdDialog,
    private toastr: ToastrService,
    private dialogService: DialogService,
    private dialogRef: MdDialogRef<HotelOfferDetailComponent>,
    @Inject(MD_DIALOG_DATA) private data: HotelOffer) {}

    onClose(){
      this.dialogRef.close();
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
