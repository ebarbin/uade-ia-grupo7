import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';

import { SearchHotel } from '../models/search-hotel.model';
import { Hotel } from '../models/hotel.model';

@Injectable()
export class HotelService {

  public searcHotel:SearchHotel;

  reset(){
      this.searcHotel = new SearchHotel();
  }
  constructor(
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}
}
