import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/Rx';

import { User } from '../shared/models/user.model';
import { PortalResponse } from '../shared/models/portal-response.model';

@Injectable()
export class AuthService {
  token: string;

  constructor(private httpClient:HttpClient, private router: Router) {}

  signin(username: string, password: string) {
    return this.httpClient.post('portal-seven-web/api/rest/user', {username:username, password:password})
    .map((response:PortalResponse)=>{
        if (response.success){
            this.token = <string>response.data;
        }
    }).toPromise();
  }

  get() {
    return this.httpClient.get('portal-seven-web/api/rest/user')
    .map((response:PortalResponse)=>{
        if (response.success){
            this.token = <string>response.data;
        }
    }).toPromise();
  }


  logout() {
    return this.httpClient.post('portal-seven-web/api/rest/user/logout', this.token)
    .map((response:PortalResponse)=>{
        if (response.success){
            this.token = <string>response.data;
        }
    }).toPromise();
  }

  isAuthenticated() {
    return this.token != null;
  }
}
