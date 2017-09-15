import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../../../auth/services/auth.service';
import { Observable } from 'rxjs/Observable';
import { ResultSearchHotelDS } from '../../models/results-search-hotel-ds';

import { SearchHotel } from '../../models/search-hotel.model';

@Component({
  selector: 'app-hotel-filter',
  templateUrl: './hotel-filter.component.html',
  styleUrls: ['./hotel-filter.component.css']
})
export class HotelFilterComponent implements OnInit {

  @ViewChild('hotelSearchForm') hotelSearchForm: NgForm;

  @Output() search: EventEmitter<SearchHotel> = new EventEmitter();

  constructor(private authService:AuthService) { }

  quantityOptions:any[] = [];
  
    options = [
      'One',
      'Two',
      'Three',
    ];
  
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
    
    onSubmit(form:NgForm){
      console.log(form);
      this.search.next(form.value);
    }
}
