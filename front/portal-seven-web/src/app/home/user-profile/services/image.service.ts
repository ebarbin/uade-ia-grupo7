import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Image } from '../models/image.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';
import { ToastrService } from 'ngx-toastr';
import { User } from '../models/user.model';
import { UserService } from './user.service';

@Injectable()
export class ImageService {

    constructor(
      private userService: UserService, 
      private toastr:ToastrService,  
      private httpClient:HttpClient){}

    uploadImage(user:User, formdata:any):Promise<PortalResponse>{
      return this.httpClient.put('portal-seven-web/api/rest/image/' + user.id, formdata)
        .map((response:PortalResponse)=>{
            return response;
        }).toPromise();
    }
}