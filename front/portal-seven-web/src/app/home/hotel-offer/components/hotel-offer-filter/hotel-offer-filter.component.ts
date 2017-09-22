import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs/Observable';

import { AutocompleteResource } from '../../../../shared/models/autocomplete-resource.model';
import { AutocompleteService } from '../../../../shared/services/hotel-autocomplete.service';
import { HotelOfferRequest } from '../../models/hotel-offer-request.model';
import { HotelOffer } from '../../models/hotel-offer.model';

@Component({
  selector: 'app-hotel-offer-filter',
  templateUrl: './hotel-offer-filter.component.html',
  styleUrls: ['./hotel-offer-filter.component.css']
})
export class HotelOfferFilterComponent implements OnInit {

  @Output('search') search: EventEmitter<HotelOfferRequest> = new EventEmitter();
  @Output('reset') reset: EventEmitter<any> = new EventEmitter();

  fromDate:Date = new Date();
  toDate:Date = new Date();
  
  constructor(
    private autocompleteService:AutocompleteService, 
    private toastr: ToastrService) { }

  quantityOptions:any[] = [];

  hotelResults:AutocompleteResource[];
  
    filterHotels(val: string) {
      if (!val) return;
      this.autocompleteService.queryHotels(val).then((resources:AutocompleteResource[]) => {
        this.hotelResults = resources;
      }).catch((res:HttpErrorResponse) => {
        if (res.error){
          if (typeof res.error != 'object') {
            this.toastr.error(JSON.parse(res.error).errorMessage)
          } else {
            this.toastr.error(res.error.errorMessage)
          }            
        } else {
          this.toastr.error(res.message);
        }
      });
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
      this.reset.next({});
    }

    onSubmit(form:NgForm){
      console.log(form.value);
      this.search.next(form.value);
    }
}
