import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MdButtonModule, MdCheckboxModule, MdDatepickerModule, 
  MdNativeDateModule, MdInputModule, MdListModule, MdAutocompleteModule,
  MdSelectModule, MdCardModule, MdChipsModule, MdTableModule,
  MdDialogModule, MdButtonToggleModule, MdPaginatorModule, MdExpansionModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MdButtonModule, MdCheckboxModule, MdDatepickerModule, MdNativeDateModule,
    MdListModule, MdInputModule, MdSelectModule, MdCardModule,
    MdChipsModule, MdTableModule, MdDialogModule, MdButtonToggleModule, MdPaginatorModule,
    MdExpansionModule
  ],
  exports: [
    MdButtonModule, MdCheckboxModule, MdDatepickerModule, MdNativeDateModule,
    MdListModule, MdInputModule, MdAutocompleteModule, MdSelectModule,
    MdCardModule, MdChipsModule, MdTableModule, MdDialogModule, MdButtonToggleModule, MdPaginatorModule,
    MdExpansionModule
  ],
  declarations: []
})
export class AngularMaterialModule { }
