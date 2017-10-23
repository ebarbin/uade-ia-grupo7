import { FavouriteHotelReservationResumeComponent } from './home/favourite/components/favourite-hotel-reservation-resume/favourite-hotel-reservation-resume.component';
import { FavouriteComponent } from './home/favourite/favourite.component';

import { PackageOfferCardResultComponent } from './home/package-offer/components/package-offer-card-result/package-offer-card-result.component';
import { PackageOfferGridResultComponent } from './home/package-offer/components/package-offer-grid-result/package-offer-grid-result.component';
import { HotelOfferCardResultComponent } from './home/hotel-offer/components/hotel-offer-card-result/hotel-offer-card-result.component';
import { HotelOfferGridResultComponent } from './home/hotel-offer/components/hotel-offer-grid-result/hotel-offer-grid-result.component';
import { HotelOfferReservationResumeComponent } from './home/hotel-offer/components/hotel-offer-reservation-resume/hotel-offer-reservation-resume.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninComponent } from './auth/components/signin/signin.component';
import { AuthGuard } from './auth/services/auth-guard.service';
import { ConfigurationComponent } from './home/configuration/configuration.component';
import { HomeComponent } from './home/home.component';
import { HotelOfferComponent } from './home/hotel-offer/hotel-offer.component';
import { PackageOfferComponent } from './home/package-offer/package-offer.component';
import { UserProfileComponent } from './home/user-profile/user-profile.component';
import { HotelOfferResultGuard } from './home/hotel-offer/services/hotel-offer-result.guard';
import { PackageOfferResultGuard } from './home/package-offer/services/package-offer-result.guard';
import { FavouriteGuard } from './home/favourite/services/favourite-guard.service';

const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent, 
    canActivate: [AuthGuard], children:[
      {path: 'hotel-offer', component: HotelOfferComponent, canActivate: [AuthGuard],
        children:[
          {path: 'result-grid', component: HotelOfferGridResultComponent, 
            canActivate: [AuthGuard, HotelOfferResultGuard]},
          {path: 'result-card', component: HotelOfferCardResultComponent, 
            canActivate: [AuthGuard, HotelOfferResultGuard]},
        ]
      },
      {path: 'hotel-authorization-resume', component: HotelOfferReservationResumeComponent, canActivate: [AuthGuard]},
      {path: 'package-offer', component: PackageOfferComponent, canActivate: [AuthGuard],
        children:[
          {path: 'result-grid', component: PackageOfferGridResultComponent, 
            canActivate: [AuthGuard, PackageOfferResultGuard]},
          {path: 'result-card', component: PackageOfferCardResultComponent, 
            canActivate: [AuthGuard, PackageOfferResultGuard]},
        ]
      },
      {path: 'user-profile', component: UserProfileComponent, canActivate: [AuthGuard]},
      {path: 'configuration', component: ConfigurationComponent, canActivate: [AuthGuard]},
      {path: 'favourite', component: FavouriteComponent, canActivate: [AuthGuard, FavouriteGuard]},
      {path: 'favorite-hotel-authorization-resume', component: FavouriteHotelReservationResumeComponent, 
        canActivate: [AuthGuard, FavouriteGuard]},
    ]},
  { path: 'signin', component: SigninComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
