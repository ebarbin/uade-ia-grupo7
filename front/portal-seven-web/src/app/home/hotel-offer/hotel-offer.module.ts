import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';

import { HotelOfferComponent } from './hotel-offer.component';
import { HotelOfferFilterComponent } from './components/hotel-offer-filter/hotel-offer-filter.component';
import { HotelOfferDetailComponent } from './components/hotel-offer-detail/hotel-offer-detail.component';
import { HotelOfferGridResultComponent } from './components/hotel-offer-grid-result/hotel-offer-grid-result.component';
import { HotelOfferCardResultComponent } from './components/hotel-offer-card-result/hotel-offer-card-result.component';
import { HotelOfferListResultComponent } from './components/hotel-offer-list-result/hotel-offer-list-result.component';

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
    HotelOfferListResultComponent,
  ],
  entryComponents: [HotelOfferDetailComponent]
})
export class HotelOfferModule { }
