import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { BlockUI, NgBlockUI } from 'ng-block-ui';

import 'rxjs/Rx';

import { User } from '../shared/models/user.model';
import { PortalResponse } from '../shared/models/portal-response.model';

@Injectable()
export class AuthService {

  @BlockUI() blockUI: NgBlockUI;
  public user: User;
  
  constructor(
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}

  signin(userName: string) {
    
    this.blockUI.start('Cargando...');

    return this.httpClient.post('portal-seven-web/api/rest/user', 
      {userName:userName})
      .map((response:PortalResponse)=>{
          return response;
      })
      .toPromise().then((response:PortalResponse)=>{
          this.blockUI.stop();
          if (response.success){
            //this.toastr.success('Operación finalizada con éxito');
            this.user = <User>response.data;
            this.router.navigate(['home']);
          }
      }).catch((res:HttpErrorResponse) => {
          this.blockUI.stop();
          this.toastr.error(res.error.errorMessage);
      });
  }

  logout() {
    this.user = null;
    this.router.navigate(['signin']);
    /*  
    return this.httpClient.post('portal-seven-web/api/rest/user/logout', this.token)
    .map((response:PortalResponse)=>{
        if (response.success){
            this.token = <string>response.data;
        }
    }).toPromise();*/
  }

  isAuthenticated() {
    return this.user != null;
  }
}
