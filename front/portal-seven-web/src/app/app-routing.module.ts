import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './auth/services/auth-guard.service';

import { SigninComponent } from './auth/components/signin/signin.component';
import { HomeComponent } from './home/home.component';
import { HotelOfferComponent } from './home/hotel-offer/hotel-offer.component';
import { PackageComponent } from './home/package/package.component';
import { UserProfileComponent } from './home/user-profile/user-profile.component';
import { ChangeImageComponent } from './home/user-profile/components/change-image/change-image.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent, 
    canActivate: [AuthGuard], children:[
      {path: 'hotel-offer', component: HotelOfferComponent, canActivate: [AuthGuard]},
      {path: 'package', component: PackageComponent, canActivate: [AuthGuard]},
      {path: 'user-profile', component: UserProfileComponent, canActivate: [AuthGuard]}
    ]},
  { path: 'signin', component: SigninComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
