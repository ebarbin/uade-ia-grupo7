import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';

import { User } from '../shared/models/user.model';
import { PortalResponse } from '../shared/models/portal-response.model';

@Injectable()
export class AuthService {
  token: string;

  constructor(
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}

  signin(username: string, password: string) {
    return this.httpClient.post('portal-seven-web/api/rest/user', 
    {username:username, password:password})
    .map((response:PortalResponse)=>{
        return response;
    }).toPromise().then((response:PortalResponse)=>{
        if (response.success){
            this.toastr.success('Operación finalizada con éxito');
            this.token = "asdasdasdasd";
            this.router.navigate(['home']);
        } else {
            this.toastr.error(response.errorMessage);
        }
    });
  }

  logout() {
    this.token = null;
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
    return this.token != null;
  }
}
