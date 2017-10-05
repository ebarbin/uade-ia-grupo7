import { PackageOffer } from './../models/package-offer.model';
import { Subject } from 'rxjs/Subject';
import { ToastrService } from 'ngx-toastr';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { PortalResponse } from './../../../shared/models/portal-response.model';
import { PackageOfferRequest } from './../models/package-offer-request.model';
import { PackageOfferHeader } from './../models/package-offer-header.model';
import { Injectable } from '@angular/core';

@Injectable()
export class PackageOfferService {

  view: string = 'card';
  packageOffer: PackageOffer;
  filterRequest: PackageOfferRequest;

  resultsChanged:Subject<PackageOfferHeader[]> = new Subject;
  packageOffers:PackageOfferHeader[] = [];

  constructor(
    private httpClient:HttpClient,
    private toastr: ToastrService) { }

  private compare(a, b, isAsc) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  sortResults(sortDirection:string, sortField:string){
    this.packageOffers = this.packageOffers.sort((a, b) => {
      let isAsc = sortDirection == 'asc';
      return this.compare(a[sortField], b[sortField], isAsc);
    });
    this.resultsChanged.next(this.packageOffers);
  }

  getDetail(packageOfferHeader:PackageOfferHeader):Promise<PackageOffer>{
    return this.httpClient.get('portal-seven-web/api/rest/package-offerr/detail/' + packageOfferHeader.id)
    .map((response:PortalResponse)=>{
      if(response.success) {
        this.packageOffer = <PackageOffer>response.data;
        return  <PackageOffer>response.data;
      } else {
        this.packageOffer = null;
        this.toastr.error(response.errorMessage);
      }
    }).toPromise();
  }

  reset(){
    this.filterRequest = null;
    this.packageOffers = [];
    this.resultsChanged.next(this.packageOffers);
  }

  search(request:PackageOfferRequest):Promise<PackageOfferHeader[]>{
    this.filterRequest = request;
    return this.httpClient.post('portal-seven-web/api/rest/package-offer/search', request)
      .map((response:PortalResponse)=>{
        if(response.success) {
          this.packageOffers = <PackageOfferHeader[]>response.data;
          this.resultsChanged.next(this.packageOffers);
          return <PackageOfferHeader[]>response.data;
        } else {
          this.toastr.error(response.errorMessage);
          this.packageOffers = [];
          this.resultsChanged.next(this.packageOffers);
          return [];
        }
      }).toPromise();
  }
}
