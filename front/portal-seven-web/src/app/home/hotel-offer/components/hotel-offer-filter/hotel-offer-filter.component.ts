import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs/Observable';

import { AutocompleteResource } from '../../../../shared/models/autocomplete-resource.model';
import { AutocompleteService } from '../../../../shared/services/hotel-autocomplete.service';
import { HotelOfferRequest } from '../../models/hotel-offer-request.model';
import { HotelOfferHeader } from '../../models/hotel-offer-header.model';
import { HotelOfferService } from '../../services/hotel-offer.service';

@Component({
  selector: 'app-hotel-offer-filter',
  templateUrl: './hotel-offer-filter.component.html',
  styleUrls: ['./hotel-offer-filter.component.css']
})
export class HotelOfferFilterComponent implements OnInit {

  roomQuantityValues:any[] = [];
  peopleQuantityValues:any[] = [];

  hotelResults:AutocompleteResource[];
  fromDate:Date = this.getToday();
  toDate:Date = this.getNextWeek();
  roomQuantity:number = 1;

  constructor(
    private hotelOfferService: HotelOfferService,
    private toastr: ToastrService,
    private autocompleteService:AutocompleteService) { }

    formValid(form:NgForm){
      if (!form.valid) return false;

      if (form.value.fromDate && form.value.toDate)
        if (form.value.fromDate > form.value.toDate)
          return false;
      
      if (form.value.minPrice && !this.isNumber(form.value.minPrice)) return false;
      if (form.value.maxPrice && !this.isNumber(form.value.maxPrice)) return false;
      if (form.value.minPrice && form.value.maxPrice && 
        parseFloat(form.value.minPrice) > parseFloat(form.value.maxPrice)) {
        return false; 
      }
      
      return true;
    }

    filterHotels(val: string) {
      if (!val) return;
      this.autocompleteService.queryHotels(val).then((resources:AutocompleteResource[]) => {
        this.hotelResults = resources;
      }).catch((res:HttpErrorResponse) => {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
    }

    autocompleteDisplaySelected(resource: AutocompleteResource): string {
      return resource ? resource.name : null;
    }

    ngOnInit() {
      this.peopleQuantityValues.push({value: -1, viewValue: '- Vacio -'});
      for(let i = 1; i <= 10;i++) {
        this.roomQuantityValues.push({value: i, viewValue: i});
        this.peopleQuantityValues.push({value: i, viewValue: i});
       }
    }
    
    onReset() {
      this.hotelOfferService.reset();
    }

    onSubmit(form:NgForm){
      this.fixForm(form);
      this.hotelOfferService.search(<HotelOfferRequest>form.value);
    }

    private fixForm(form:NgForm){
      form.value.hotel = form.value.hotel && typeof form.value.hotel == 'object' ? form.value.hotel : null;
      form.value.peoplePerRoom = form.value.peoplePerRoom == -1 || form.value.peoplePerRoom == '' ? null : form.value.peoplePerRoom;
      form.value.roomQuantity = form.value.roomQuantity == -1 || form.value.roomQuantity == '' ? null : form.value.roomQuantity;
    }

    private isNumber(n:any) {
      return !isNaN(parseFloat(n)) && isFinite(n);
    }

    private getToday(){
      return new Date();
    }
  
    private getNextWeek(){
      var d = new Date();
      d.setDate(d.getDate() + 7);
      return d;
    }
}
