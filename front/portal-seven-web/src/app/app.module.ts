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

import { AuthGuard } from './auth/services/auth-guard.service';
import { AuthService } from './auth/services/auth.service';
import { HotelService } from './home/hotel/services/hotel.service';

import { MyHttpInterceptor } from './shared/services/my-http-interceptor';

import { AppComponent } from './app.component';
import { SigninComponent } from './auth/signin/signin.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './home/header/header.component';
import { HotelComponent } from './home/hotel/hotel.component';
import { PackageComponent } from './home/package/package.component';
import { DropdownDirective } from './shared/directives/dropdown.directive';
import { MapComponent } from './shared/components/map/map.component';
import { ImageGetterPipe } from './shared/pipes/image-getter.pipe';
import { HotelFilterComponent } from './home/hotel/components/hotel-filter/hotel-filter.component';
import { HotelGridResultComponent } from './home/hotel/components/hotel-grid-result/hotel-grid-result.component';
import { HotelDetailComponent } from './home/hotel/components/hotel-detail/hotel-detail.component';
import { HotelCardResultComponent } from './home/hotel/components/hotel-card-result/hotel-card-result.component';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    HomeComponent,
    HeaderComponent,
    HotelComponent,
    PackageComponent,
    DropdownDirective,
    MapComponent,
    ImageGetterPipe,
    HotelFilterComponent,
    HotelGridResultComponent,
    HotelDetailComponent,
    HotelCardResultComponent
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
    HotelService,
    {provide: LOCALE_ID, useValue: 'es-AR'},
    { provide: HTTP_INTERCEPTORS, useClass: MyHttpInterceptor, multi: true }
  ],
  entryComponents:[
    HotelDetailComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
