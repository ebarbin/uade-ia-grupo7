import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm, FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';

import { AuthService } from '../../auth/services/auth.service';

import { SearchHotel } from './models/search-hotel.model';

@Component({
  selector: 'app-search-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {

  constructor(private authService:AuthService) { }

  ngOnInit() {}

  onSearch(searchHotel: SearchHotel){
    console.log(searchHotel);
  }
  
}
