import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';
import { SearchHotel } from './search-hotel.model';

@Injectable()
export class SearchHotelService {

  public searcHotel:SearchHotel;

  reset(){
      this.searcHotel = new SearchHotel();
  }
  constructor(
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}
}
