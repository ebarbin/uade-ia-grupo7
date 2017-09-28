import { PackageOffer } from './../models/package-offer.model';
import { Subject } from 'rxjs/Subject';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { PortalResponse } from './../../../shared/models/portal-response.model';
import { PackageOfferRequest } from './../models/package-offer-request.model';
import { PackageOfferHeader } from './../models/package-offer-header.model';
import { Injectable } from '@angular/core';

@Injectable()
export class PackageOfferService {

  resultsChanged:Subject<PackageOfferHeader[]> = new Subject;

  constructor(
    private httpClient:HttpClient,
    private toastr: ToastrService) { }

  getDetail(packageOfferHeader:PackageOfferHeader):Promise<PackageOffer>{
    return this.httpClient.get('portal-seven-web/api/rest/package-offerr/detail/' + packageOfferHeader.id)
    .map((response:PortalResponse)=>{
      if(response.success) {
        var packageOffer = <PackageOffer>response.data;

        /*hotelOffer.hotel.rooms = hotelOffer.hotel.rooms.filter((room)=>{
          return room.id != hotelOffer.room.id;
        });*/

        return PackageOffer;
      }
    }).toPromise();
  }

  search(request:PackageOfferRequest):Promise<PackageOfferHeader[]>{
    return this.httpClient.post('portal-seven-web/api/rest/package-offer/search', request)
      .map((response:PortalResponse)=>{
        if(response.success) {
          var results = <PackageOfferHeader[]>response.data;
          if (results.length == 0) this.toastr.info('No hay resultados.')
          this.resultsChanged.next(results);
          return results;
        }
      }).toPromise();
  }
}
