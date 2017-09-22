import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

import { AutocompleteResource } from '../models/autocomplete-resource.model';
import { PortalResponse } from '../models/portal-response.model';

@Injectable()
export class AutocompleteService {

  constructor(
    private httpClient:HttpClient, 
    private toastr: ToastrService) { }

  queryHotels(value:String):Promise<AutocompleteResource[]>{
    return this.httpClient.get('portal-seven-web/api/rest/autocomplete/hotel/' + value + '/5')
      .map((response:PortalResponse)=>{
        return response;
      }).map((response:PortalResponse)=> {
        if (response.success){
          return <AutocompleteResource[]>response.data;
        } else {
          return [];
        }
      }).toPromise();
  }
}
