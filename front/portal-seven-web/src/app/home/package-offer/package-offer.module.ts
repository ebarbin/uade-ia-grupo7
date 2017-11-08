import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';

import { PackageOfferComponent } from './package-offer.component';
import { PackageOfferFilterComponent } from './components/package-offer-filter/package-offer-filter.component';
import { PackageOfferCardResultComponent } from './components/package-offer-card-result/package-offer-card-result.component';
import { PackageOfferGridResultComponent } from './components/package-offer-grid-result/package-offer-grid-result.component';
import { PackageOfferDetailComponent } from './components/package-offer-detail/package-offer-detail.component';
import { PackageOfferConfirmComponent } from './components/package-offer-confirm/package-offer-confirm.component';
import { PackageOfferReservationResumeComponent } from './components/package-offer-reservation-resume/package-offer-reservation-resume.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [
    PackageOfferComponent,
    PackageOfferFilterComponent,
    PackageOfferCardResultComponent,
    PackageOfferGridResultComponent,
    PackageOfferDetailComponent,
    PackageOfferConfirmComponent,
    PackageOfferReservationResumeComponent
  ],
  entryComponents:[PackageOfferDetailComponent, PackageOfferConfirmComponent]
})
export class PackageOfferModule { }
