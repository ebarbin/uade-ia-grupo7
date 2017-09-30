import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';

import { PackageOfferComponent } from './package-offer.component';
import { PackageOfferFilterComponent } from './components/package-offer-filter/package-offer-filter.component';
import { PackageOfferCardResultComponent } from './components/package-offer-card-result/package-offer-card-result.component';
import { PackageOfferGridResultComponent } from './components/package-offer-grid-result/package-offer-grid-result.component';
import { PackageOfferListResultComponent } from './components/package-offer-list-result/package-offer-list-result.component';
import { PackageOfferDetailComponent } from './components/package-offer-detail/package-offer-detail.component';

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
    PackageOfferListResultComponent,
    PackageOfferDetailComponent
  ],
  entryComponents:[PackageOfferDetailComponent]
})
export class PackageOfferModule { }
