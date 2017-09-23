import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LOCALE_ID, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AgmCoreModule } from '@agm/core';

import { AngularFontAwesomeModule } from 'angular-font-awesome/angular-font-awesome';
import { BlockUIModule } from 'ng-block-ui';
import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './auth/components/signin/signin.component';
import { AuthGuard } from './auth/services/auth-guard.service';
import { AuthService } from './auth/services/auth.service';
import { ConfigurationComponent } from './home/configuration/configuration.component';
import { HeaderComponent } from './home/header/header.component';
import { HomeComponent } from './home/home.component';
import {
    HotelOfferCardResultComponent
} from './home/hotel-offer/components/hotel-offer-card-result/hotel-offer-card-result.component';
import {
    HotelOfferDetailComponent
} from './home/hotel-offer/components/hotel-offer-detail/hotel-offer-detail.component';
import {
    HotelOfferFilterComponent
} from './home/hotel-offer/components/hotel-offer-filter/hotel-offer-filter.component';
import {
    HotelOfferGridResultComponent
} from './home/hotel-offer/components/hotel-offer-grid-result/hotel-offer-grid-result.component';
import {
    HotelOfferListResultComponent
} from './home/hotel-offer/components/hotel-offer-list-result/hotel-offer-list-result.component';
import { HotelOfferComponent } from './home/hotel-offer/hotel-offer.component';
import {
    PackageOfferCardResultComponent
} from './home/package-offer/components/package-offer-card-result/package-offer-card-result.component';
import {
    PackageOfferDetailComponent
} from './home/package-offer/components/package-offer-detail/package-offer-detail.component';
import {
    PackageOfferFilterComponent
} from './home/package-offer/components/package-offer-filter/package-offer-filter.component';
import {
    PackageOfferGridResultComponent
} from './home/package-offer/components/package-offer-grid-result/package-offer-grid-result.component';
import {
    PackageOfferListResultComponent
} from './home/package-offer/components/package-offer-list-result/package-offer-list-result.component';
import { PackageOfferComponent } from './home/package-offer/package-offer.component';
import {
    ChangeImageComponent
} from './home/user-profile/components/change-image/change-image.component';
import { ImageService } from './home/user-profile/services/image.service';
import { UserService } from './home/user-profile/services/user.service';
import { UserProfileComponent } from './home/user-profile/user-profile.component';
import { AngularMaterialModule } from './shared/angular-material/angular-material.module';
import { MapComponent } from './shared/components/map/map.component';
import { DropdownDirective } from './shared/directives/dropdown.directive';
import { ImageGetterPipe } from './shared/pipes/image-getter.pipe';
import { AutocompleteService } from './shared/services/hotel-autocomplete.service';
import { MyHttpInterceptor } from './shared/services/my-http-interceptor';
import { HotelOfferService } from './home/hotel-offer/services/hotel-offer.service';
import { ErrorHandlerService } from './shared/services/error-handler.service';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    HomeComponent,
    HeaderComponent,
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
    ConfigurationComponent,
    PackageOfferComponent,
    PackageOfferFilterComponent,
    PackageOfferCardResultComponent,
    PackageOfferGridResultComponent,
    PackageOfferListResultComponent,
    PackageOfferDetailComponent
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
    AutocompleteService,
    HotelOfferService,
    ErrorHandlerService,
    {provide: LOCALE_ID, useValue: 'es-AR'},
    { provide: HTTP_INTERCEPTORS, useClass: MyHttpInterceptor, multi: true }
  ],
  entryComponents:[
    HotelOfferDetailComponent, PackageOfferDetailComponent, ChangeImageComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
