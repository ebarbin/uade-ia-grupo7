import { PortalResponse } from './../../models/portal-response.model';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class EvaluationStartService {

  constructor(
    private httpClient:HttpClient, 
    private toastr: ToastrService) { }

  hotelValoration(hotelOfferId:number, valoration:number):Promise<number>{
    return this.httpClient.put('portal-seven-web/api/rest/hotel-offer/valoration/' + hotelOfferId, {vote: valoration})
    .map((response:PortalResponse)=>{
      if (response.success) {
        return <number>response.data;
      } else {
        return 0;
      }
      }).toPromise();
  }
}