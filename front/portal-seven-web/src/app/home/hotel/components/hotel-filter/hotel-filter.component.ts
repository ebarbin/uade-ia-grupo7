import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { SearchHotel } from '../../models/search-hotel.model';
import { Hotel } from '../../models/hotel.model';

@Component({
  selector: 'app-hotel-filter',
  templateUrl: './hotel-filter.component.html',
  styleUrls: ['./hotel-filter.component.css']
})
export class HotelFilterComponent implements OnInit {

  @Output('search') search: EventEmitter<SearchHotel> = new EventEmitter();
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
