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

    private fixForm(form:NgForm){
      form.value.destination = form.value.destination && typeof form.value.destination == 'object' ? form.value.destination : null;
      form.value.peoplePerRoom = form.value.peoplePerRoom == -1 ? null : form.value.peoplePerRoom;
    }

    onSubmit(form:NgForm){
      this.search.next(form.value);
    }
}
