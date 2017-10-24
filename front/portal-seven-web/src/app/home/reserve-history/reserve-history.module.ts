import { SharedModule } from './../../shared/shared.module';
import { ReserveHistoryComponent } from './reserve-history.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [ReserveHistoryComponent]
  ,
  exports:[ReserveHistoryComponent]
})
export class ReserveHistoryModule { }
