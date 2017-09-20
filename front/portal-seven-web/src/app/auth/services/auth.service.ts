import { Router } from '@angular/router';
import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import 'rxjs/Rx';

import { User } from '../../home/user-profile/models/user.model';
import { PortalResponse } from '../../shared/models/portal-response.model';
import { UserService } from '../../home/user-profile/services/user.service';

@Injectable()
export class AuthService implements OnInit {

  user: User;
  
  constructor(
    private userService:UserService,
    private httpClient:HttpClient, 
    private router: Router,
    private toastr: ToastrService) {}

  ngOnInit(){
    this.userService.userChanged.subscribe((user:User)=>{
      this.user = user;
    })
  }

  signin(userName: string) {
    return this.httpClient.get('portal-seven-web/api/rest/user/' + userName)
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
            //var jsonErr = JSON.parse(res.error);
            //this.toastr.error(jsonErr.errorMessage);
            this.toastr.error(res.error.errorMessage)
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
