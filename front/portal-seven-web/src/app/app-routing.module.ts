import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninComponent } from './auth/components/signin/signin.component';
import { AuthGuard } from './auth/services/auth-guard.service';
import { ConfigurationComponent } from './home/configuration/configuration.component';
import { HomeComponent } from './home/home.component';
import { HotelOfferComponent } from './home/hotel-offer/hotel-offer.component';
import { PackageOfferComponent } from './home/package-offer/package-offer.component';
import {
    ChangeImageComponent
} from './home/user-profile/components/change-image/change-image.component';
import { UserProfileComponent } from './home/user-profile/user-profile.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent, 
    canActivate: [AuthGuard], children:[
      {path: 'hotel-offer', component: HotelOfferComponent, canActivate: [AuthGuard]},
      {path: 'package', component: PackageOfferComponent, canActivate: [AuthGuard]},
      {path: 'user-profile', component: UserProfileComponent, canActivate: [AuthGuard]},
      {path: 'configuration', component: ConfigurationComponent, canActivate: [AuthGuard]}
    ]},
  { path: 'signin', component: SigninComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
