import { Component, OnInit, OnDestroy } from '@angular/core';

import { Subscription } from 'rxjs/Subscription';

import { AuthService } from '../../auth/services/auth.service';
import { UserService } from '../user-profile/services/user.service';

import { User } from '../user-profile/models/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {

  userSubs:Subscription;

  constructor(private userService:UserService, private authService:AuthService) { }

  user: User;

  ngOnInit() {
    this.user = this.authService.getUser();
    this.userSubs = this.userService.userChanged.subscribe((user:User)=>{
      this.user = Object.assign({}, user);
    });
  }

  ngOnDestroy() {
    this.userSubs.unsubscribe();
  }

  logout(){
    this.authService.logout();
  }
}
