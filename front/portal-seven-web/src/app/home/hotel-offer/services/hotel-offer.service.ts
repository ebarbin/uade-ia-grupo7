import { Holder } from './../../../shared/models/holder.interface';
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
import { Router } from '@angular/router';

@Injectable()
export class HotelOfferService implements Holder {

  private view: string = 'card';
  private filterRequest:HotelOfferRequest;
  private hotelOffers: HotelOfferHeader[] = [];
  private hotelOffer: HotelOffer;
  //Necesario porque el grid utiliza otra estructura
  private resultsChanged: Subject<HotelOfferHeader[]> = new Subject;
  
  constructor(
    private router:Router,
    private httpClient: HttpClient,
    private toastr: ToastrService) {}

    getResultsChanged(): Subject<HotelOfferHeader[]>{
      return this.resultsChanged;
    }

    getResults():HotelOfferHeader[]{
      return this.hotelOffers;
    }

    getSelected():HotelOffer{
      return this.hotelOffer;
    }

    setView(view:string){
      this.view = view;
    }

    getView():string {
      return this.view;
    }

    getType():string {
      return 'hotel';
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
      this.router.navigate(['/home/hotel-offer']);
    }

    search(request:HotelOfferRequest){
      //HACK guard reevaluate
      this.router.navigate(['/home/hotel-offer']);

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
        }).toPromise().then((results:HotelOfferHeader[]) => {
          this.router.navigate(['home/hotel-offer/result-' + this.getView()]);
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

    authorizeReservation(hotelOffer:HotelOffer):Promise<AuthorizeStatus>{
      return this.httpClient.get('portal-seven-web/api/rest/hotel-offer/authorize/' + hotelOffer.id)
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
