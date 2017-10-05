import { PackageOfferService } from './../../../home/package-offer/services/package-offer.service';
import { HotelOfferService } from './../../../home/hotel-offer/services/hotel-offer.service';
import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-change-result',
  templateUrl: './view-change-result.component.html',
  styleUrls: ['./view-change-result.component.css']
})
export class ViewChangeResultComponent implements OnInit {

  @Input()type:string;

  constructor(
    public pckSrv: PackageOfferService,
    public htlSrv: HotelOfferService,
    private router: Router) { }
  
  onChangeView(view){
    this.view = view;
    if (this.type == 'hotel') {
      this.htlSrv.view = view;
      this.router.navigate(['home/hotel-offer/result-' + view]);
    } else if (this.type == 'package'){
      this.pckSrv.view = view;
      this.router.navigate(['home/package-offer/result-' + view]);
    }
  }

  view: string;
  ngOnInit(){
    if (this.type == 'hotel') {
      this.view = this.htlSrv.view 
    } else if (this.type == 'package'){
      this.view = this.pckSrv.view 
    }
  }
}
