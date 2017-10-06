import { Holder } from './../../models/holder.interface';
import { PackageOfferService } from './../../../home/package-offer/services/package-offer.service';
import { HotelOfferService } from './../../../home/hotel-offer/services/hotel-offer.service';
import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-change-result',
  templateUrl: './view-change-result.component.html',
  styleUrls: ['./view-change-result.component.css']
})
export class ViewChangeResultComponent {

  @Input()holder:Holder;

  constructor(
    private router: Router) { }
  
  onChangeView(view){
    this.holder.setView(view);
    if (this.holder.getType() == 'hotel') {
      this.router.navigate(['home/hotel-offer/result-' + view]);
    } else if (this.holder.getType() == 'package'){
      this.router.navigate(['home/package-offer/result-' + view]);
    }
  }
}
