import { FavouriteHotelReservationResumeComponent } from './components/favourite-hotel-reservation-resume/favourite-hotel-reservation-resume.component';
import { FavouriteComponent } from './favourite.component';
import { SharedModule } from './../../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [FavouriteComponent, FavouriteHotelReservationResumeComponent],
  exports: [],
})
export class FavouriteModule { }
