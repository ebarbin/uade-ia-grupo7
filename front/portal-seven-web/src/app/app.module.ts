import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFontAwesomeModule } from 'angular-font-awesome/angular-font-awesome';
import { BlockUIModule } from 'ng-block-ui';
import { AgmCoreModule } from '@agm/core';
import { AngularMaterialModule } from './shared/angular-material/angular-material.module';

import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { ImageService } from './home/user-profile/services/image.service';
import { UserService } from './home/user-profile/services/user.service';
import { AuthGuard } from './auth/services/auth-guard.service';
import { AuthService } from './auth/services/auth.service';
import { HotelService } from './home/hotel-offer/services/hotel.service';
import { HotelAutocompleteService } from './home/hotel-offer/components/hotel-offer-filter/services/hotel-autocomplete.service';

import { MyHttpInterceptor } from './shared/services/my-http-interceptor';

import { DropdownDirective } from './shared/directives/dropdown.directive';

import { ImageGetterPipe } from './shared/pipes/image-getter.pipe';

import { AppComponent } from './app.component';
import { SigninComponent } from './auth/components/signin/signin.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './home/header/header.component';
import { HotelOfferComponent } from './home/hotel-offer/hotel-offer.component';
import { PackageComponent } from './home/package/package.component';
import { MapComponent } from './shared/components/map/map.component';

import { HotelOfferFilterComponent } from './home/hotel-offer/components/hotel-offer-filter/hotel-offer-filter.component';
import { HotelOfferGridResultComponent } from './home/hotel-offer/components/hotel-offer-grid-result/hotel-offer-grid-result.component';
import { HotelOfferDetailComponent } from './home/hotel-offer/components/hotel-offer-detail/hotel-offer-detail.component';
import { HotelOfferCardResultComponent } from './home/hotel-offer/components/hotel-offer-card-result/hotel-offer-card-result.component';
import { UserProfileComponent } from './home/user-profile/user-profile.component';
import { ChangeImageComponent } from './home/user-profile/components/change-image/change-image.component';
import { HotelOfferListResultComponent } from './home/hotel-offer/components/hotel-offer-list-result/hotel-offer-list-result.component';
import { ConfigurationComponent } from './home/configuration/configuration.component';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    HomeComponent,
    HeaderComponent,
    PackageComponent,
    DropdownDirective,
    MapComponent,
    ImageGetterPipe,
    HotelOfferComponent,
    HotelOfferFilterComponent,
    HotelOfferDetailComponent,
    HotelOfferGridResultComponent,
    HotelOfferCardResultComponent,
    HotelOfferListResultComponent,
    UserProfileComponent,
    ChangeImageComponent,
    ConfigurationComponent
  ],
  imports: [
    BrowserModule,
    AngularMaterialModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularFontAwesomeModule,
    BlockUIModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCAyih95bNn9V9XXdUe6DQaLGCw_R0_uXA'
    }),
    ToastrModule.forRoot({
      positionClass: 'toast-top-center',
      preventDuplicates: true,
    })
  ],
  providers: [
    AuthGuard, 
    AuthService,
    UserService,
    ImageService,
    HotelAutocompleteService,
    HotelService,
    {provide: LOCALE_ID, useValue: 'es-AR'},
    { provide: HTTP_INTERCEPTORS, useClass: MyHttpInterceptor, multi: true }
  ],
  entryComponents:[
    HotelOfferDetailComponent, ChangeImageComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
