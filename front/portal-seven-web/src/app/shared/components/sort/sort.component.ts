import { Component, OnInit, Input, Output } from '@angular/core';
import { HotelOfferService } from '../../../home/hotel-offer/services/hotel-offer.service';

@Component({
  selector: 'app-sort',
  templateUrl: './sort.component.html',
  styleUrls: ['./sort.component.css']
})
export class SortComponent implements OnInit {

  @Input()type:string;

  sortField:string = '';
  sortDirection:string = 'desc';

  toogleSortDirection() {
    if(this.sortDirection == 'asc') this.sortDirection = 'desc';
    else this.sortDirection = 'asc';

    if(this.type == 'hotel') {
      this.hotelOfferService.sortResults(this.sortDirection, this.sortField);
    }
  }

  onSortChange(){
    this.sortDirection = 'desc'
    if(this.type == 'hotel') {
      this.hotelOfferService.sortResults(this.sortDirection, this.sortField);
    }
  }

  getSortValues() {
    if (this.type == 'hotel') {
      return [
        {value:'', viewValue:''},
        {value:'name', viewValue:'Nombre Hotel'},
        {value:'description', viewValue:'Descripción'},
        {value:'price', viewValue:'Precio'},
        {value:'roomCapacity', viewValue:'Capacidad'},
        {value:'offerStart', viewValue:'Desde'},
        {value:'offerEnd', viewValue:'Hasta'}
      ];
    } 
  }
  constructor(private hotelOfferService:HotelOfferService) { }

  ngOnInit() {}

}
