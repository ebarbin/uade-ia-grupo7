import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MdButtonModule, MdCheckboxModule, MdDatepickerModule, 
  MdNativeDateModule, MdInputModule, MdListModule, MdAutocompleteModule,
  MdSelectModule, MdCardModule, MdChipsModule, MdTableModule,
  MdDialogModule, MdButtonToggleModule, MdPaginatorModule, MdExpansionModule,
  MdTooltipModule, MdTabsModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MdButtonModule, MdCheckboxModule, MdDatepickerModule, MdNativeDateModule,
    MdListModule, MdInputModule, MdSelectModule, MdCardModule,
    MdChipsModule, MdTableModule, MdDialogModule, MdButtonToggleModule, MdPaginatorModule,
    MdExpansionModule, MdTooltipModule, MdTabsModule
  ],
  exports: [
    MdButtonModule, MdCheckboxModule, MdDatepickerModule, MdNativeDateModule,
    MdListModule, MdInputModule, MdAutocompleteModule, MdSelectModule,
    MdCardModule, MdChipsModule, MdTableModule, MdDialogModule, MdButtonToggleModule, MdPaginatorModule,
    MdExpansionModule, MdTooltipModule, MdTabsModule
  ],
  declarations: []
})
export class AngularMaterialModule { }
