import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Image } from '../../../shared/models/image.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';
import { ToastrService } from 'ngx-toastr';
import { PortalUser } from '../models/portal-user.model';
import { UserService } from './user.service';

@Injectable()
export class ImageService {

    constructor(
      private toastr:ToastrService,  
      private httpClient:HttpClient){}

    uploadImage(user:PortalUser, formdata:any):Promise<PortalResponse>{
      return this.httpClient.put('portal-seven-web/api/rest/image/' + user.id, formdata)
        .map((response:PortalResponse)=>{
            return response;
        }).toPromise();
    }
}