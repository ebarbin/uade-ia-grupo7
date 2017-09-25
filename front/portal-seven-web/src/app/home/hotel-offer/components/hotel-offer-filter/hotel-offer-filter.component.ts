import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs/Observable';

import { AutocompleteResource } from '../../../../shared/models/autocomplete-resource.model';
import { AutocompleteService } from '../../../../shared/services/hotel-autocomplete.service';
import { HotelOfferRequest } from '../../models/hotel-offer-request.model';
import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { ErrorHandlerService } from '../../../../shared/services/error-handler.service';

@Component({
  selector: 'app-hotel-offer-filter',
  templateUrl: './hotel-offer-filter.component.html',
  styleUrls: ['./hotel-offer-filter.component.css']
})
export class HotelOfferFilterComponent implements OnInit {

  @Output('search') search: EventEmitter<HotelOfferRequest> = new EventEmitter();
  @Output('reset') reset: EventEmitter<any> = new EventEmitter();

  quantityOptions:any[] = [];
  hotelResults:AutocompleteResource[];
  fromDate:Date = null
  toDate:Date = null;
  
  constructor(
    private errorHandlerService:ErrorHandlerService,
    private autocompleteService:AutocompleteService, 
    private toastr: ToastrService) { }

    formValid(form:NgForm){
      if (!form.valid) return false;

      if (form.value.fromDate && form.value.toDate){
        if (form.value.fromDate > form.value.toDate){
          return false;
        }
      }

      return true;
    }

    filterHotels(val: string) {
      if (!val) return;
      this.autocompleteService.queryHotels(val).then((resources:AutocompleteResource[]) => {
        this.hotelResults = resources;
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
      form.value.hotel = form.value.hotel && typeof form.value.hotel == 'object' ? form.value.hotel : null;
      form.value.peoplePerRoom = form.value.peoplePerRoom == -1 ? null : form.value.peoplePerRoom;
      form.value.roomQuantity = form.value.roomQuantity == -1 ? null : form.value.roomQuantity;
    }

    onSubmit(form:NgForm){
      this.fixForm(form)
      
      this.search.next(form.value);
    }
}
