import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm, FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';

import { HotelService } from './services/hotel.service';

import { SearchHotel } from './models/search-hotel.model';
import { Hotel } from './models/hotel.model';

@Component({
  selector: 'app-search-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {

  empty:boolean = true;

  constructor(
    private hotelService:HotelService) { }

  ngOnInit() {}

  onReset(){
    this.empty = true;
    this.hotelService.hotelResults.next([]);
  }

  onSearch(searchHotel: SearchHotel){
    //BUSCAR LOS HOTELES/OFERTAS??
    console.log(searchHotel);

    var results = [
      new Hotel(1, 'Hotel 1', null),
      new Hotel(2, 'Hotel 2', null),
      new Hotel(3, 'Hotel 3', null),
      new Hotel(4, 'Hotel 4', null)
    ];
    
    this.empty = false;
    this.hotelService.hotelResults.next(results);
  }
  
}
