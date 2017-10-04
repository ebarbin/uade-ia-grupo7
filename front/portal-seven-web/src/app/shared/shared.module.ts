import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AngularMaterialModule } from './angular-material/angular-material.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from '../app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFontAwesomeModule } from 'angular-font-awesome/angular-font-awesome';
import { BlockUIModule } from 'ng-block-ui';
import { NgxGalleryModule } from 'ngx-gallery';
import { AgmCoreModule } from '@agm/core';
import { ToastrModule } from 'ngx-toastr';

import { TruncatePipe } from './pipes/truncate.pipe';
import { FirstPipe } from './pipes/first.pipe';
import { WhenNullPutBlankPipe } from './pipes/when-null-put-blank.pipe';
import { ImageGetterPipe } from './pipes/image-getter.pipe';
import { JoinPipe } from './pipes/join.pipe';

import { ImgGalleryComponent } from './components/img-gallery/img-gallery.component';
import { ConfirmComponent } from './components/confirm/confirm.component';
import { MapComponent } from './components/map/map.component';

import { DropdownDirective } from './directives/dropdown.directive';
import { SortComponent } from './components/sort/sort.component';
import { ViewChangeResultComponent } from './components/view-change-result/view-change-result.component';

@NgModule({
  imports: [
    CommonModule, AngularMaterialModule, FormsModule, HttpClientModule,
    AppRoutingModule, BrowserAnimationsModule, AngularFontAwesomeModule,
    BlockUIModule, NgxGalleryModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCAyih95bNn9V9XXdUe6DQaLGCw_R0_uXA'
    }),
    ToastrModule.forRoot({
      timeOut:2000,
      positionClass: 'toast-top-center',
      preventDuplicates: true,
    }),
  ],
  exports: [
    AngularMaterialModule, FormsModule, HttpClientModule, AppRoutingModule,
    BrowserAnimationsModule, AngularFontAwesomeModule, BlockUIModule,
    NgxGalleryModule, AgmCoreModule, ToastrModule, JoinPipe,
    TruncatePipe, FirstPipe, ImageGetterPipe, WhenNullPutBlankPipe,
    ImgGalleryComponent, MapComponent, ConfirmComponent, DropdownDirective, 
    SortComponent, ViewChangeResultComponent
  ],
  declarations: [
    JoinPipe, TruncatePipe, FirstPipe, ImageGetterPipe,
    WhenNullPutBlankPipe, ImgGalleryComponent, ConfirmComponent,
    MapComponent, DropdownDirective, SortComponent, ViewChangeResultComponent,
  ],
  entryComponents: [ConfirmComponent]
})
export class SharedModule { }
