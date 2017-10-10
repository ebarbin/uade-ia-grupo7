import { Holder } from './../../../shared/models/holder.interface';
import { PackageOffer } from './../models/package-offer.model';
import { Subject } from 'rxjs/Subject';
import { ToastrService } from 'ngx-toastr';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { PortalResponse } from './../../../shared/models/portal-response.model';
import { PackageOfferRequest } from './../models/package-offer-request.model';
import { PackageOfferHeader } from './../models/package-offer-header.model';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class PackageOfferService implements Holder {

  private view: string = 'card';
  private packageOffer: PackageOffer;
  private filterRequest: PackageOfferRequest;

  private resultsChanged:Subject<PackageOfferHeader[]> = new Subject;
  private packageOffers:PackageOfferHeader[] = [];

  constructor(
    private router: Router,
    private httpClient: HttpClient,
    private toastr: ToastrService) { }

  getResults():PackageOfferHeader[]{
    return this.packageOffers;  
  }

  getSelected():PackageOffer{
    return this.packageOffer;
  }

  getResultsChanged():Subject<PackageOfferHeader[]>{
    return this.resultsChanged;
  }

  getFilter():PackageOfferRequest{
    return this.filterRequest;
  }

  getSortValues():any[]{
    return [
      {value:'', viewValue:'Seleccione...'},
      {value:'description', viewValue:'Descripción'},
      {value:'price', viewValue:'Precio'},
      {value:'offerStart', viewValue:'Desde'},
      {value:'offerEnd', viewValue:'Hasta'}
    ];
  }

  sortResults(sortDirection:string, sortField:string){
    this.packageOffers = this.packageOffers.sort((a, b) => {
      let isAsc = sortDirection == 'asc';
      return this.compare(a[sortField], b[sortField], isAsc);
    });
    this.resultsChanged.next(this.packageOffers);
  }

  setView(view:string){
    this.view = view;
  }

  getView():string {
    return this.view;
  }

  getType():string {
    return 'package';
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
    this.view = 'card';
    this.filterRequest = null;
    this.packageOffer = null;
    this.packageOffers = [];
    this.resultsChanged.next(this.packageOffers);
    this.router.navigate(['/home/package-offer']);
  }

  search(request:PackageOfferRequest){
    this.router.navigate(['/home/package-offer']);
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
      }).toPromise().then((results:PackageOfferHeader[]) => {
          this.router.navigate(['home/package-offer/result-' + this.getView()]);
      }).catch((res:HttpErrorResponse) => {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
  }

  private compare(a, b, isAsc) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
}
