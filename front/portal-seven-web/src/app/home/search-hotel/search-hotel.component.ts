import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm, FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-search-hotel',
  templateUrl: './search-hotel.component.html',
  styleUrls: ['./search-hotel.component.css']
})
export class SearchHotelComponent implements OnInit {

  @ViewChild('hotelSearchForm') hotelSearchForm: NgForm;

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
    console.log(form.value);
  }
}
