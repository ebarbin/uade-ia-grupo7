import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './auth/auth-guard.service';

import { SigninComponent } from './auth/signin/signin.component';
import { HomeComponent } from './home/home.component';
import { SearchHotelComponent } from './home/search-hotel/search-hotel.component';
import { SearchPackageComponent } from './home/search-package/search-package.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent, 
  children:[
    {path: 'hotel', component: SearchHotelComponent},
    {path: 'package', component: SearchPackageComponent}
  ]},
  { path: 'signin', component: SigninComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }