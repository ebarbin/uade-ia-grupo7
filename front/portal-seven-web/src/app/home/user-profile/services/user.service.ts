import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import { Subject } from 'rxjs/Subject';

import { User } from '../models/user.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';
import { ErrorHandlerService } from '../../../shared/services/error-handler.service';

@Injectable()
export class UserService {

  userChanged: Subject<User> = new Subject();

  constructor(
    private errorHandlerService:ErrorHandlerService,
    private httpClient: HttpClient,
    private toastr:ToastrService) { }

  update(user:User){
    this.httpClient.put('portal-seven-web/api/rest/user/'+ user.id, user)
      .map((response:PortalResponse)=>{
      return response;
    }).toPromise().then((response:PortalResponse) => {
      if (response.success) {
        this.userChanged.next(<User>response.data);
        this.toastr.success('Usuario actualizado exitosamente.');
      } else {
        this.toastr.error(response.errorMessage);
      }
    }).catch((res:HttpErrorResponse) => {
      this.errorHandlerService.set(res);
    });
  }
}
