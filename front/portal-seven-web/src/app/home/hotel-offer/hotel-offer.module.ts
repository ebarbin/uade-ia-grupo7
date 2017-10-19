import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';

import { HotelOfferComponent } from './hotel-offer.component';
import { HotelOfferFilterComponent } from './components/hotel-offer-filter/hotel-offer-filter.component';
import { HotelOfferDetailComponent } from './components/hotel-offer-detail/hotel-offer-detail.component';
import { HotelOfferGridResultComponent } from './components/hotel-offer-grid-result/hotel-offer-grid-result.component';
import { HotelOfferCardResultComponent } from './components/hotel-offer-card-result/hotel-offer-card-result.component';
import { HotelOfferConfirmComponent } from './components/hotel-offer-confirm/hotel-offer-confirm.component';
import { HotelOfferReservationResumeComponent } from './components/hotel-offer-reservation-resume/hotel-offer-reservation-resume.component';
import { HotelOfferDetailRoomComponent } from './components/hotel-offer-detail-room/hotel-offer-detail-room.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [
    HotelOfferComponent,
    HotelOfferFilterComponent,
    HotelOfferDetailComponent,
    HotelOfferGridResultComponent,
    HotelOfferCardResultComponent,
    HotelOfferConfirmComponent,
    HotelOfferReservationResumeComponent,
    HotelOfferDetailRoomComponent,
  ],
  entryComponents: [HotelOfferDetailComponent, HotelOfferConfirmComponent]
})
export class HotelOfferModule { }
