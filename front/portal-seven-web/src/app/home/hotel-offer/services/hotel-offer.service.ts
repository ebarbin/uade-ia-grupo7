import { AuthorizeStatus } from './../../../shared/models/authorize-status.model';
import { Room } from './../models/room.model';
import { HotelOfferOtherRoomsRequest } from './../models/hotel-offer-other-room-request.model';
import { Subject } from 'rxjs/Subject';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';

import { HotelOfferRequest } from '../models/hotel-offer-request.model';
import { HotelOfferHeader } from '../models/hotel-offer-header.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';
import { HotelOffer } from '../models/hotel-offer.model';

@Injectable()
export class HotelOfferService {

  hotelOffers: HotelOfferHeader[] = [];
  hotelOffer: HotelOffer;

  //Necesario porque el grid utiliza otra estructura
  resultsChanged: Subject<HotelOfferHeader[]> = new Subject;
  
  view: string = 'card';
  filterRequest:HotelOfferRequest;

  constructor(
    private httpClient: HttpClient,
    private toastr: ToastrService) {}

    sortResults(sortDirection:string, sortField:string){
      this.hotelOffers = this.hotelOffers.sort((a, b) => {
        let isAsc = sortDirection == 'asc';
        return this.compare(a[sortField], b[sortField], isAsc);
      });
      this.resultsChanged.next(this.hotelOffers);
    }
    
    getDetail(hotelOfferHeader:HotelOfferHeader):Promise<HotelOffer>{
      return this.httpClient.get('portal-seven-web/api/rest/hotel-offer/detail/' + hotelOfferHeader.id)
      .map((response:PortalResponse)=>{
        if(response.success) {
          this.hotelOffer = <HotelOffer>response.data;
          return <HotelOffer>response.data
        } else {
          this.hotelOffer = null;
          this.toastr.error(response.errorMessage);
          return null;
        }
      }).toPromise();
    }

    reset(){
      this.filterRequest = null;
      this.hotelOffers = [];
      this.resultsChanged.next(this.hotelOffers);
    }

    search(request:HotelOfferRequest):Promise<HotelOfferHeader[]>{
      this.filterRequest = request;
      return this.httpClient.post('portal-seven-web/api/rest/hotel-offer/search', request)
        .map((response:PortalResponse)=>{
          if(response.success) {
            this.hotelOffers = <HotelOfferHeader[]>response.data;
            this.resultsChanged.next(this.hotelOffers);
            return <HotelOfferHeader[]>response.data;
          } else {
            this.toastr.error(response.errorMessage);
            this.hotelOffers = [];
            this.resultsChanged.next(this.hotelOffers);
            return [];
          }
        }).toPromise();
    }

    searchOtherRooms(request:HotelOfferOtherRoomsRequest):Promise<Room[]>{
      request.fromDate = this.filterRequest.fromDate;
      request.toDate = this.filterRequest.toDate;
      return this.httpClient.post('portal-seven-web/api/rest/hotel-offer/search/other-rooms', request)
        .map((response:PortalResponse)=>{
          if(response.success) {
            return <Room[]>response.data;
          } else {
            this.toastr.error(response.errorMessage);
            return [];
          }
        }).toPromise();
    }

    authorizeReservation(hotelOffer:HotelOffer):Promise<AuthorizeStatus>{
      return this.httpClient.put('portal-seven-web/api/rest/hotel-offer/authorize/' + hotelOffer.id, {})
        .map((response:PortalResponse)=>{
          if(response.success) {
            return <AuthorizeStatus>response.data;
          } else {
            this.toastr.error(response.errorMessage);
            return null;
          }
        }).toPromise();
    }

    private compare(a, b, isAsc) {
      return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }
}
