import { ToastrService } from 'ngx-toastr';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm, FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';

import { HotelOfferRequest } from './models/hotel-offer-request.model';
import { HotelOfferHeader } from './models/hotel-offer-header.model';
import { HotelOfferService } from './services/hotel-offer.service';
import { PortalResponse } from '../../shared/models/portal-response.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-hotel-offer',
  templateUrl: './hotel-offer.component.html',
  styleUrls: ['./hotel-offer.component.css']
})
export class HotelOfferComponent implements OnInit {

  constructor(
    private toastr:ToastrService,
    private hotelOfferService:HotelOfferService) { }

  ngOnInit() {
    this.hotelOfferService.reset();
  }

  view:string = 'card';

  onChangeView(view){
    this.view = view;
  }

  hasResults(){
    return this.hotelOfferService.getResults().length > 0;
  }

  onReset(){
    this.hotelOfferService.reset();
  }

  onSearch(hotelOfferRequest: HotelOfferRequest){
    this.hotelOfferService.search(hotelOfferRequest);
  }
}
