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

import { AuthGuard } from './auth/auth-guard.service';
import { AuthService } from './auth/auth.service';
import { SearchHotelService } from './home/search-hotel/search-hotel.service';

import { MyHttpInterceptor } from './shared/services/my-http-interceptor';

import { AppComponent } from './app.component';
import { SigninComponent } from './auth/signin/signin.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './home/header/header.component';
import { SearchHotelComponent } from './home/search-hotel/search-hotel.component';
import { SearchPackageComponent } from './home/search-package/search-package.component';
import { DropdownDirective } from './shared/directives/dropdown.directive';
import { MapComponent } from './shared/components/map/map.component';
import { ImageGetterPipe } from './shared/pipes/image-getter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    HomeComponent,
    HeaderComponent,
    SearchHotelComponent,
    SearchPackageComponent,
    DropdownDirective,
    MapComponent,
    ImageGetterPipe
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
    SearchHotelService,
    {provide: LOCALE_ID, useValue: 'es-AR'},
    { provide: HTTP_INTERCEPTORS, useClass: MyHttpInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
