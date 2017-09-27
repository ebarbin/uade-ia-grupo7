import { Router } from '@angular/router';
import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';

import { PortalUser } from '../../home/user-profile/models/portal-user.model';
import { PortalResponse } from '../../shared/models/portal-response.model';
import { UserService } from '../../home/user-profile/services/user.service';
import { ErrorHandlerService } from '../../shared/services/error-handler.service';

@Injectable()
export class AuthService implements OnInit {

  private user: PortalUser;
  
  constructor(
    private errorHandlerService:ErrorHandlerService,
    private userService:UserService,
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}

  ngOnInit(){
    this.userService.userChanged.subscribe((user:PortalUser)=>{
      this.user = user;
    })
  }

  public getUser(){
    return this.user ? Object.assign({}, this.user) : null;
  }

  public setUser(user:PortalUser){
    this.user = user;
  }

  signin(userName: string) {
    return this.httpClient.get('portal-seven-web/api/rest/portal-user/' + userName)
      .map((response:PortalResponse)=>{
          return response;
      })
      .toPromise().then((response:PortalResponse)=>{
          if (response.success){
            this.user = <PortalUser>response.data;
            this.router.navigate(['home']);
          }
      }).catch((res:HttpErrorResponse) => {
        this.errorHandlerService.set(res);
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
