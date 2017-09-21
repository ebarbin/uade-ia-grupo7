import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import { PortalResponse } from '../../../../../shared/models/portal-response.model';

import { HotelAutocomplete } from '../models/hotel-autocomplete.model'

@Injectable()
export class HotelAutocompleteService {

  constructor(
    private httpClient:HttpClient, 
    private toastr: ToastrService) { }

  query(value:String):Promise<HotelAutocomplete[]>{
    return this.httpClient.get('portal-seven-web/api/rest/hotel-autocomplete/' + value + '/5')
      .map((response:PortalResponse)=>{
        return response;
      }).map((response:PortalResponse)=> {
        if (response.success){
          return <HotelAutocomplete[]>response.data;
        } else {
          return [];
        }
      }).toPromise();
  }
}
