
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HotelOfferModule } from './home/hotel-offer/hotel-offer.module';
import { SharedModule } from './shared/shared.module';
import { PackageOfferModule } from './home/package-offer/package-offer.module';
import { UserProfileModule } from './home/user-profile/user-profile.module';
import { FavouriteModule } from './home/favourite/favourite.module';

import { AppComponent } from './app.component';
import { SigninComponent } from './auth/components/signin/signin.component';
import { ConfigurationComponent } from './home/configuration/configuration.component';
import { HeaderComponent } from './home/header/header.component';
import { HomeComponent } from './home/home.component';

import { AuthGuard } from './auth/services/auth-guard.service';
import { AuthService } from './auth/services/auth.service';
import { ImageService } from './home/user-profile/services/image.service';
import { UserService } from './home/user-profile/services/user.service';
import { AutocompleteService } from './shared/services/hotel-autocomplete.service';
import { MyHttpInterceptor } from './shared/services/my-http-interceptor';
import { HotelOfferService } from './home/hotel-offer/services/hotel-offer.service';
import { PackageOfferService } from './home/package-offer/services/package-offer.service';
import { FavouriteOfferService } from './home/favourite/services/favourite-offer.service';
import { HotelOfferResultGuard } from './home/hotel-offer/services/hotel-offer-result.guard';
import { PackageOfferResultGuard } from './home/package-offer/services/package-offer-result.guard';

@NgModule({
  declarations: [
    AppComponent, SigninComponent, HomeComponent, 
    HeaderComponent, ConfigurationComponent
  ],
  imports: [
    BrowserModule, SharedModule, HotelOfferModule,
    PackageOfferModule, UserProfileModule, FavouriteModule
  ],
  providers: [
    AuthGuard, AuthService, UserService, ImageService, 
    AutocompleteService, HotelOfferService, PackageOfferService, 
    HotelOfferResultGuard, PackageOfferResultGuard, FavouriteOfferService,
    { provide: LOCALE_ID, useValue: 'es-AR' },
    { provide: HTTP_INTERCEPTORS, useClass: MyHttpInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
