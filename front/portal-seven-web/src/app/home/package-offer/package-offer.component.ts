import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { PackageOfferService } from './services/package-offer.service';
import { PackageOfferHeader } from './models/package-offer-header.model';
import { PackageOfferRequest } from './models/package-offer-request.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-package',
  templateUrl: './package-offer.component.html',
  styleUrls: ['./package-offer.component.css']
})
export class PackageOfferComponent implements OnInit {

  constructor(
    private toastr:ToastrService,
    private packageOfferService: PackageOfferService) { }

  ngOnInit() {
    this.packageOfferService.reset();
  }

  view:string = 'card';

  onChangeView(view){
    this.view = view;
  }

  hasResults(){
    return this.packageOfferService.getResults().length > 0;
  }
}
