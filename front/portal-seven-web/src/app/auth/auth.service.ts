import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';

import { User } from '../shared/models/user.model';
import { PortalResponse } from '../shared/models/portal-response.model';

@Injectable()
export class AuthService {

  public user: User;
  
  constructor(
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}

  signin(userName: string) {
    return this.httpClient.post('portal-seven-web/api/rest/user', 
      {userName:userName})
      .map((response:PortalResponse)=>{
          return response;
      })
      .toPromise().then((response:PortalResponse)=>{
          if (response.success){
            this.user = <User>response.data;
            this.router.navigate(['home/hotel']);
          }
      }).catch((res:HttpErrorResponse) => {
          if (res.error){
            this.toastr.error(res.error.errorMessage);
          } else {
            this.toastr.error(res.message);
          }
      });
  }

  logout() {
    this.user = null;
    this.router.navigate(['signin']);
  }

  isAuthenticated() {
    return this.user != null;
  }
}
