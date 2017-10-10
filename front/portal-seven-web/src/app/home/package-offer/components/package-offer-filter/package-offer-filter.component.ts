import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

import { AutocompleteResource } from '../../../../shared/models/autocomplete-resource.model';
import { AutocompleteService } from '../../../../shared/services/hotel-autocomplete.service';
import { PackageOfferRequest } from '../../models/package-offer-request.model';
import { PackageOfferService } from '../../services/package-offer.service';
import { PackageOfferHeader } from '../../models/package-offer-header.model';

@Component({
  selector: 'app-package-offer-filter',
  templateUrl: './package-offer-filter.component.html',
  styleUrls: ['./package-offer-filter.component.css']
})
export class PackageOfferFilterComponent implements OnInit {

  quantityOptions:any[] = [];
  fromDate:Date = null;
  toDate:Date = null;
  quantityPeople:number = 1;

  constructor(
    private packageOfferService: PackageOfferService,
    private autocompleteService:AutocompleteService, 
    private toastr: ToastrService) { }

  restinationResults:AutocompleteResource[];
  
    filterDestinations(val: string) {
      if (!val) return;
      this.autocompleteService.queryDestinations(val).then((resources:AutocompleteResource[]) => {
        this.restinationResults = resources;
      }).catch((res:HttpErrorResponse) => {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
    }

    formValid(form:NgForm){
      if (!form.valid) return false;

      if (form.value.fromDate && form.value.toDate)
        if (form.value.fromDate > form.value.toDate)
          return false;
        
      return true;
    }
    
    autocompleteDisplaySelected(resource: AutocompleteResource): string {
      return resource ? resource.name : null;
    }

    ngOnInit() {
      for(let i = 1; i <= 10;i++) {
        this.quantityOptions.push({value: i, viewValue: i});
       }
    }

    onReset() {
      this.packageOfferService.reset();
    }

    onSubmit(form:NgForm){
      this.fixForm(form);
      this.packageOfferService.search(<PackageOfferRequest>form.value);
    }

    private fixForm(form:NgForm){
      form.value.destination = form.value.destination && typeof form.value.destination == 'object' ? form.value.destination : null;
      form.value.quantityPeople = form.value.quantityPeople == -1 || form.value.quantityPeople == '' ? null : form.value.quantityPeople;
    }
}
