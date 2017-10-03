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

  resultsChanged:Subject<HotelOfferHeader[]> = new Subject;
  results:HotelOfferHeader[] = [];

  constructor(
    private httpClient:HttpClient,
    private toastr: ToastrService) {}

    private compare(a, b, isAsc) {
      return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }

    getResults():HotelOfferHeader[]{
      return this.results;
    }

    sortResults(sortDirection:string, sortField:string){
      this.results = this.results.sort((a, b) => {
        let isAsc = sortDirection == 'asc';
        return this.compare(a[sortField], b[sortField], isAsc);
      });
      this.resultsChanged.next(this.results);
    }
    
    getDetail(hotelOfferHeader:HotelOfferHeader):Promise<HotelOffer>{
      return this.httpClient.get('portal-seven-web/api/rest/hotel-offer/detail/' + hotelOfferHeader.id)
      .map((response:PortalResponse)=>{
        if(response.success) {
          var hotelOffer = <HotelOffer>response.data;

          //Como la habitacion de la oferta, esta dentro de las habitacion del hotel
          //Debo quitarla de la lista de habitaciones, para no verla nuevamente.
          hotelOffer.hotel.rooms = hotelOffer.hotel.rooms.filter((room)=>{
            return room.id != hotelOffer.room.id;
          });

          return hotelOffer;
        } else {
          this.toastr.error(response.errorMessage);
        }
      }).toPromise();
    }

    filterRequest:HotelOfferRequest;

    reset(){
      this.filterRequest = null;
      this.results = [];
      this.resultsChanged.next(this.results);
    }

    search(request:HotelOfferRequest){
      this.filterRequest = request;
      return this.httpClient.post('portal-seven-web/api/rest/hotel-offer/search', request)
        .map((response:PortalResponse)=>{
          if(response.success) {
            return <HotelOfferHeader[]>response.data;
          } else {
            this.toastr.error(response.errorMessage);
            return [];
          }
        }).toPromise().then((results:HotelOfferHeader[]) => {
          this.results = results;
          if (this.results.length == 0) this.toastr.info('No hay resultados.');
          this.resultsChanged.next(this.results);
        }).catch((res:HttpErrorResponse) => {
          this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
        });
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
}
