import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import { Subject } from 'rxjs/Subject';

import { PortalUser } from '../models/portal-user.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';
import { NewPortalUser } from '../models/new-portal-user.modal';
import { ImageService } from './image.service';

@Injectable()
export class UserService {

  userChanged: Subject<PortalUser> = new Subject();

  constructor(
    private imageService:ImageService,
    private httpClient: HttpClient,
    private toastr:ToastrService) { }

  addNewUser(user: PortalUser):Promise<PortalResponse>{
    return this.httpClient.post('portal-seven-web/api/rest/portal-user', user)
      .map((response:PortalResponse)=>{
          return response;
      }).toPromise();
  }

  update(user: PortalUser){
    this.httpClient.put('portal-seven-web/api/rest/portal-user/'+ user.id, user)
      .map((response:PortalResponse)=>{
      return response;
    }).toPromise().then((response:PortalResponse) => {
      if (response.success) {
        this.userChanged.next(<PortalUser>response.data);
        this.toastr.success('Usuario actualizado con éxito.');
      } else {
        this.toastr.error(response.errorMessage);
      }
    }).catch((res:HttpErrorResponse) => {
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }
}
