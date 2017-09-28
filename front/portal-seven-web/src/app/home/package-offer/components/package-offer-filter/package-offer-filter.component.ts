import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

import { AutocompleteResource } from '../../../../shared/models/autocomplete-resource.model';
import { AutocompleteService } from '../../../../shared/services/hotel-autocomplete.service';
import { PackageOfferRequest } from '../../models/package-offer-request.model';
import { ErrorHandlerService } from '../../../../shared/services/error-handler.service';

@Component({
  selector: 'app-package-offer-filter',
  templateUrl: './package-offer-filter.component.html',
  styleUrls: ['./package-offer-filter.component.css']
})
export class PackageOfferFilterComponent implements OnInit {

  @Output('search') search: EventEmitter<PackageOfferRequest> = new EventEmitter();
  @Output('reset') reset: EventEmitter<any> = new EventEmitter();

  fromDate:Date = null;
  toDate:Date = null;
  
  constructor(
    private errorHandlerService:ErrorHandlerService,
    private autocompleteService:AutocompleteService, 
    private toastr: ToastrService) { }

  quantityOptions:any[] = [];

  restinationResults:AutocompleteResource[];
  
    filterDestinations(val: string) {
      if (!val) return;
      this.autocompleteService.queryDestinations(val).then((resources:AutocompleteResource[]) => {
        this.restinationResults = resources;
      }).catch((res:HttpErrorResponse) => {
        this.errorHandlerService.set(res);
      });
    }

    formValid(form:NgForm){
      if (!form.valid) return false;

      /*var today = new Date();
      if(form.value.fromDate && form.value.fromDate < today)
        return false;
      if(form.value.toDate && form.value.toDate < today)
        return false;*/

      if (form.value.fromDate && form.value.toDate)
        if (form.value.fromDate > form.value.toDate)
          return false;
        
      return true;
    }
    
    autocompleteDisplaySelected(resource: AutocompleteResource): string {
      return resource ? resource.name : null;
    }

    ngOnInit() {
      this.quantityOptions.push({value: -1, viewValue: '- Vacio -'});
      for(let i = 1; i <= 10;i++) {
        this.quantityOptions.push({value: i, viewValue: i});
       }
    }
    
    onReset() {
      this.reset.next({});
    }

    onSubmit(form:NgForm){
      this.fixForm(form);
      this.search.next(form.value);
    }

    private fixForm(form:NgForm){
      form.value.destination = form.value.destination && typeof form.value.destination == 'object' ? form.value.destination : null;
      form.value.quantityPeople = form.value.quantityPeople == -1 || form.value.quantityPeople == '' ? null : form.value.quantityPeople;
    }
}
