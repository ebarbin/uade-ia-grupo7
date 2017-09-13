import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm, FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-search-hotel',
  templateUrl: './search-hotel.component.html',
  styleUrls: ['./search-hotel.component.css']
})
export class SearchHotelComponent implements OnInit {

  @ViewChild('hotelSearchForm') hotelSearchForm: NgForm;

  constructor() { }

  roomsQuantity = [
    {value: '1', viewValue: 'Una'},
    {value: '2', viewValue: 'Dos'},
    {value: '3', viewValue: 'Tres'},
    {value: '4', viewValue: 'Cuatro'},
    {value: '5', viewValue: 'Cinco'},
    {value: '6', viewValue: 'Seis'}
  ];

  peoplePerRoom = [
    {value: '1', viewValue: 'Una'},
    {value: '2', viewValue: 'Dos'},
    {value: '3', viewValue: 'Tres'},
    {value: '4', viewValue: 'Cuatro'},
    {value: '5', viewValue: 'Cinco'},
    {value: '6', viewValue: 'Seis'}
  ];

  options = [
    'One',
    'Two',
    'Three',
    ''
  ];

  filteredOptions: Observable<string[]>;

  ngOnInit() {
    this.hotelSearchForm.valueChanges.subscribe(data=>{
      data.hotel = data.hotel ? this.filter(data.hotel) : this.options.slice();      
  });  /*this.hotelSearchForm.valueChanges.map(data =>{ console.log(data); return data;}).startWith(null)
    .map(val => val ? this.filter(val) : this.options.slice());*/
  }

  filter(val: string): string[] {
    return this.options.filter(option =>
      option.toLowerCase().indexOf(val.toLowerCase()) === 0);
 }

  onSubmit(form:NgForm){
    console.log(form.value);
  }
}
