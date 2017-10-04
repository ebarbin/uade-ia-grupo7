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

  resultsChanged:Subject<PackageOfferHeader[]> = new Subject;
  view: string = 'card';
  results:PackageOfferHeader[] = [];

  constructor(
    private httpClient:HttpClient,
    private toastr: ToastrService) { }

  private compare(a, b, isAsc) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  getResults():PackageOfferHeader[]{
    return this.results;
  }

  sortResults(sortDirection:string, sortField:string){
    this.results = this.results.sort((a, b) => {
      let isAsc = sortDirection == 'asc';
      return this.compare(a[sortField], b[sortField], isAsc);
    });
    this.resultsChanged.next(this.results);
  }

  getDetail(packageOfferHeader:PackageOfferHeader):Promise<PackageOffer>{
    return this.httpClient.get('portal-seven-web/api/rest/package-offerr/detail/' + packageOfferHeader.id)
    .map((response:PortalResponse)=>{
      if(response.success) {
        var packageOffer = <PackageOffer>response.data;

        /*hotelOffer.hotel.rooms = hotelOffer.hotel.rooms.filter((room)=>{
          return room.id != hotelOffer.room.id;
        });*/

        return PackageOffer;
      } else {
        this.toastr.error(response.errorMessage);
      }
    }).toPromise();
  }

  reset(){
    this.results = [];
    this.resultsChanged.next(this.results);
  }

  search(request:PackageOfferRequest){
    return this.httpClient.post('portal-seven-web/api/rest/package-offer/search', request)
      .map((response:PortalResponse)=>{
        if(response.success) {
          return <PackageOfferHeader[]>response.data;
        } else {
          this.toastr.error(response.errorMessage);
          return [];
        }
      }).toPromise().then((results:PackageOfferHeader[]) => {
        this.results = results;
        if (this.results.length == 0) this.toastr.info('No hay resultados.');
        this.resultsChanged.next(this.results);
      }).catch((res:HttpErrorResponse) => {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      });
  }
}
