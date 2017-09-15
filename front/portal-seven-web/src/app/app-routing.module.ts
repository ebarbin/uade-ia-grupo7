import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './auth/services/auth-guard.service';

import { SigninComponent } from './auth/components/signin/signin.component';
import { HomeComponent } from './home/home.component';
import { HotelComponent } from './home/hotel/hotel.component';
import { PackageComponent } from './home/package/package.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent, 
    canActivate: [AuthGuard],
    children:[
      {path: 'hotel', component: HotelComponent, canActivate: [AuthGuard],
      children:[]
    },
    {path: 'package', component: PackageComponent, canActivate: [AuthGuard]}
    ]},
  { path: 'signin', component: SigninComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
