import { PackageOfferService } from './../../../home/package-offer/services/package-offer.service';
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

  constructor(
    private packageOfferService:PackageOfferService,
    private hotelOfferService:HotelOfferService) { }

  toogleSortDirection() {
    if (this.sortDirection == 'asc') this.sortDirection = 'desc';
    else this.sortDirection = 'asc';

    if(this.type == 'hotel') {
      this.hotelOfferService.sortResults(this.sortDirection, this.sortField);
    } else if (this.type == 'package'){
      this.packageOfferService.sortResults(this.sortDirection, this.sortField);
    }
  }

  onSortChange(){
    if (this.type == 'hotel') {
      this.hotelOfferService.sortResults(this.sortDirection, this.sortField);
    } else if (this.type == 'package'){
      this.packageOfferService.sortResults(this.sortDirection, this.sortField);
    }
  }

  getSortDirectionDescription(){
    return this.sortDirection == 'asc' ? 'Ascendente' : 'Descendente';
  }

  getSortValues() {
    if (this.type == 'hotel') {
      return [
        {value:'', viewValue:'Seleccione...'},
        {value:'name', viewValue:'Hotel'},
        {value:'price', viewValue:'Precio'},
        {value:'roomCapacity', viewValue:'Capacidad'},
        {value:'offerStart', viewValue:'Desde'},
        {value:'offerEnd', viewValue:'Hasta'}
      ];
    } else if (this.type == 'package') {
      return [];
    }
  }
  
  ngOnInit() {}
}
