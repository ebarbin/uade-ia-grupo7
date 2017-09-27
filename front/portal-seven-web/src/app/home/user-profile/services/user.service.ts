import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import { Subject } from 'rxjs/Subject';

import { PortalUser } from '../models/portal-user.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';
import { ErrorHandlerService } from '../../../shared/services/error-handler.service';

@Injectable()
export class UserService {

  userChanged: Subject<PortalUser> = new Subject();

  constructor(
    private errorHandlerService:ErrorHandlerService,
    private httpClient: HttpClient,
    private toastr:ToastrService) { }

  update(user: PortalUser){
    this.httpClient.put('portal-seven-web/api/rest/user/'+ user.id, user)
      .map((response:PortalResponse)=>{
      return response;
    }).toPromise().then((response:PortalResponse) => {
      if (response.success) {
        this.userChanged.next(<PortalUser>response.data);
        this.toastr.success('Usuario actualizado exitosamente.');
      } else {
        this.toastr.error(response.errorMessage);
      }
    }).catch((res:HttpErrorResponse) => {
      this.errorHandlerService.set(res);
    });
  }
}
