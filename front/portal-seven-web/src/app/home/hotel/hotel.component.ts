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

  constructor(
    private hotelService:HotelService) { }

  ngOnInit() {}

  view:string = 'grid';

  onChangeView(){
    if(this.view == 'grid'){
      this.view = 'card';
    } else {
      this.view = 'grid';
    }
  }

  onReset(){
    this.results = [];
  }

  results:Hotel[] = [];
  onSearch(searchHotel: SearchHotel){
    //BUSCAR LOS HOTELES/OFERTAS??
    console.log(searchHotel);

    this.results = [
      new Hotel(1, 'Hotel 1', null),
      new Hotel(2, 'Hotel 2', null),
      new Hotel(3, 'Hotel 3', null),
      new Hotel(4, 'Hotel 4', null),
      new Hotel(5, 'Hotel 5', null),
      new Hotel(6, 'Hotel 6', null),
      new Hotel(7, 'Hotel 7', null)
    ];
  }
  
}
