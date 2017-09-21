import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';

import { HotelOfferRequest } from '../models/hotel-offer-request.model';

@Injectable()
export class HotelService {

  public hotelOfferRequest:HotelOfferRequest;

  constructor(
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}
}
