import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { HttpErrorResponse } from '@angular/common/http';

import { ToastrService } from 'ngx-toastr';
import { HotelAutocompleteService } from './services/hotel-autocomplete.service';

import { HotelAutocomplete } from './models/hotel-autocomplete.model';

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
    private autocompleteService:HotelAutocompleteService, 
    private toastr: ToastrService) { }

  quantityOptions:any[] = [];

  hotelResults:HotelAutocomplete[];
  
    filterHotels(val: string) {
      if (!val) return;
      this.autocompleteService.query(val).then((data:HotelAutocomplete[]) => {
        this.hotelResults = data;
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

    autocompleteDisplaySelected(hotelAutocomplete: HotelAutocomplete): string {
      return hotelAutocomplete ? hotelAutocomplete.name : null;
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
