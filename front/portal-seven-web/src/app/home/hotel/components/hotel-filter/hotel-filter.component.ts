import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import { SearchHotel } from '../../models/search-hotel.model';

@Component({
  selector: 'app-hotel-filter',
  templateUrl: './hotel-filter.component.html',
  styleUrls: ['./hotel-filter.component.css']
})
export class HotelFilterComponent implements OnInit {

  @Output() search: EventEmitter<SearchHotel> = new EventEmitter();
  @Output() reset: EventEmitter<null> = new EventEmitter();

  constructor() { }

  quantityOptions:any[] = [];
  
    options = ['One','Two','Three'];
  
    filteredOptions: Observable<string[]>;
  
    filterStates(val: string) {
      if (val) {
        const filterValue = val.toLowerCase();
        return this.options.filter(hotel => hotel.toLowerCase().startsWith(filterValue));
      }
      return this.options;
    }
  
    ngOnInit() {
      for(let i = 1; i <= 10;i++) {
        this.quantityOptions.push({value: i, viewValue: i});
       }
    }
    
    onReset() {
      this.reset.next();
    }

    onSubmit(form:NgForm){
      this.search.next(form.value);
    }
}
