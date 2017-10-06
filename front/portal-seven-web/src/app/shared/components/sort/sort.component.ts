import { Holder } from './../../models/holder.interface';
import { PackageOfferService } from './../../../home/package-offer/services/package-offer.service';
import { Component, Input } from '@angular/core';
import { HotelOfferService } from '../../../home/hotel-offer/services/hotel-offer.service';

@Component({
  selector: 'app-sort',
  templateUrl: './sort.component.html',
  styleUrls: ['./sort.component.css']
})
export class SortComponent {

  @Input()holder:Holder;

  sortField:string = '';
  sortDirection:string = 'desc';

  constructor(
    private packageOfferService:PackageOfferService,
    private hotelOfferService:HotelOfferService) { }

  toogleSortDirection() {
    if (this.sortDirection == 'asc') this.sortDirection = 'desc';
    else this.sortDirection = 'asc';

    this.holder.sortResults(this.sortDirection, this.sortField);
  }

  onSortChange(){
    this.holder.sortResults(this.sortDirection, this.sortField);
  }

  getSortDirectionDescription(){
    return this.sortDirection == 'asc' ? 'Ascendente' : 'Descendente';
  }
}
