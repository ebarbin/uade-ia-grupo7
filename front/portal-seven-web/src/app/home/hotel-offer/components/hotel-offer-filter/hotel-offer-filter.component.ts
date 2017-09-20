import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

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
  
  constructor() { }

  quantityOptions:any[] = [];
  
    hotels:any[];
    
  
    filteredOptions: Observable<string[]>;
  
    filterStates(val: string) {
      var options = ['One','Two','Three'];
      if (val) {
        const filterValue = val.toLowerCase();
        this.hotels =options.filter(hotel => hotel.toLowerCase().startsWith(filterValue));
      }
      this.hotels = options;
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
      this.search.next(form.value);
    }
}
