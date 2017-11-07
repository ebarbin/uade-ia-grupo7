import { PackageOfferGuard } from './home/package-offer/services/package-offer-guard.service';
import { HotelOfferGuard } from './home/hotel-offer/services/hotel-offer-guard.service';
import { SystemConfigurationGuard } from './shared/services/system-configuration-guard.service';
import { EvaluationStartService } from './shared/components/evaluation-start/evaluation-start.service';
import { ConfigurationGuard } from './home/configuration/services/configuration-guard.service';
import { ReserveHistoryGuard } from './home/reserve-history/services/reserve-history-guard.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HotelOfferModule } from './home/hotel-offer/hotel-offer.module';
import { SharedModule } from './shared/shared.module';
import { PackageOfferModule } from './home/package-offer/package-offer.module';
import { UserProfileModule } from './home/user-profile/user-profile.module';
import { FavouriteModule } from './home/favourite/favourite.module';
import { ReserveHistoryModule } from './home/reserve-history/reserve-history.module';

import { AppComponent } from './app.component';
import { SigninComponent } from './auth/components/signin/signin.component';
import { ConfigurationComponent } from './home/configuration/configuration.component';
import { HeaderComponent } from './home/header/header.component';
import { HomeComponent } from './home/home.component';

import { AuthGuard } from './auth/services/auth-guard.service';
import { AuthService } from './auth/services/auth.service';
import { ImageService } from './home/user-profile/services/image.service';
import { UserService } from './home/user-profile/services/user.service';
import { AutocompleteService } from './shared/services/autocomplete.service';
import { MyHttpInterceptor } from './shared/services/my-http-interceptor';
import { HotelOfferService } from './home/hotel-offer/services/hotel-offer.service';
import { PackageOfferService } from './home/package-offer/services/package-offer.service';
import { FavouriteOfferService } from './home/favourite/services/favourite-offer.service';
import { HotelOfferResultGuard } from './home/hotel-offer/services/hotel-offer-result.guard';
import { PackageOfferResultGuard } from './home/package-offer/services/package-offer-result.guard';
import { FavouriteGuard } from './home/favourite/services/favourite-guard.service';
import { ConfigurationService } from './home/configuration/services/configuration.service';

@NgModule({
  declarations: [
    AppComponent, SigninComponent, HomeComponent, 
    HeaderComponent, ConfigurationComponent
  ],
  imports: [
    BrowserModule, SharedModule, HotelOfferModule,
    PackageOfferModule, UserProfileModule, FavouriteModule, ReserveHistoryModule
  ],
  providers: [
    AuthService, UserService, ImageService, 
    AutocompleteService, HotelOfferService, PackageOfferService, 
    FavouriteOfferService, EvaluationStartService, ConfigurationService,
    AuthGuard, FavouriteGuard, ReserveHistoryGuard, 
    PackageOfferResultGuard, HotelOfferResultGuard, ConfigurationGuard,
    SystemConfigurationGuard, HotelOfferGuard, PackageOfferGuard,
    { provide: LOCALE_ID, useValue: 'es-AR' },
    { provide: HTTP_INTERCEPTORS, useClass: MyHttpInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
