import { PackageAuthorizeRequest } from './../../../shared/models/package-authorize-request.model';
import { AuthService } from './../../../auth/services/auth.service';
import { AuthorizeStatus } from './../../../shared/models/authorize-status.model';
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
import { SimpleNamed } from '../../../shared/models/simple-named.model';

@Injectable()
export class PackageOfferService implements Holder {

  private view: string = 'card';
  private packageOffer: PackageOffer;
  private filterRequest: PackageOfferRequest;

  private resultsChanged:Subject<PackageOfferHeader[]> = new Subject;
  private packageOffers:PackageOfferHeader[] = [];

  constructor(
    private authService: AuthService,
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
    return this.httpClient.get('portal-seven-web/api/rest/package-offer/detail/' + packageOfferHeader.id)
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

  authorizeReservation():Promise<AuthorizeStatus>{
    var req = new PackageAuthorizeRequest(this.filterRequest.quantityPeople);
    return this.httpClient.put('portal-seven-web/api/rest/package-offer/authorize/' +  this.packageOffer.id, req)
      .map((response:PortalResponse)=>{
        if(response.success) {
          return <AuthorizeStatus>response.data;
        } else {
          this.toastr.error(response.errorMessage);
          return null;
        }
      }).toPromise();
  }

  search(request:PackageOfferRequest){
    this.router.navigate(['/home/package-offer']);

    this.filterRequest = request;
    return this.httpClient.post('portal-seven-web/api/rest/package-offer/search/' + this.authService.getUser().id, request)
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

  searchOtherPackages():Promise<PackageOfferHeader[]>{

    if(!this.filterRequest.destination)
      this.filterRequest.destination = new SimpleNamed(this.packageOffer.destination.id, '');

    return this.httpClient
      .put('portal-seven-web/api/rest/package-offer/search/other-packages/'+ 
        this.packageOffer.id, 
        this.filterRequest)
          .map((response:PortalResponse)=>{
            if(response.success) {
              return <PackageOfferHeader[]>response.data;
            } else {
              this.toastr.error(response.errorMessage);
              return [];
            }
          }).toPromise();
  }

  private compare(a, b, isAsc) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
}
