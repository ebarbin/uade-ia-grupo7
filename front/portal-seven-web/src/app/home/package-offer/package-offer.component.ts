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

  ngOnInit() {}

  view:string = 'grid';

  onChangeView(view){
    this.view = view;
  }

  onReset(){
    this.results = [];
  }

  results:PackageOfferHeader[] = [];
  onSearch(packageOfferRequest: PackageOfferRequest){
    this.packageOfferService.search(packageOfferRequest)
    .then((results:PackageOfferHeader[]) => {
     this.results = results;
    }).catch((res:HttpErrorResponse) => {
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
   });
  }

}
