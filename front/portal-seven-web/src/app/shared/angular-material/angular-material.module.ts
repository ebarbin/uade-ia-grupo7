import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MdButtonModule, MdCheckboxModule, MdDatepickerModule, 
  MdNativeDateModule, MdInputModule, MdListModule, MdAutocompleteModule,
  MdSelectModule, MdCardModule, MdChipsModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MdButtonModule,
    MdCheckboxModule,
    MdDatepickerModule,
    MdNativeDateModule,
    MdListModule,
    MdInputModule,
    MdSelectModule,
    MdCardModule,
    MdChipsModule
  ],
  exports: [
    MdButtonModule,
    MdCheckboxModule,
    MdDatepickerModule,
    MdNativeDateModule,
    MdListModule,
    MdInputModule,
    MdAutocompleteModule,
    MdSelectModule,
    MdCardModule,
    MdChipsModule
  ],
  declarations: []
})
export class AngularMaterialModule { }
