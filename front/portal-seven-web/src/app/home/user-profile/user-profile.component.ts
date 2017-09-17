import { Component, OnInit, OnDestroy } from '@angular/core';
import { MdDialog } from '@angular/material';

import { Subscription } from 'rxjs/Subscription';

import { AuthService } from '../../auth/services/auth.service';
import { UserService } from './services/user.service';

import { User } from './models/user.model';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { ChangeImageComponent } from './components/change-image/change-image.component';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit, OnDestroy {

  userSubs:Subscription;

  user:User;

  constructor(
    private dialog: MdDialog,
    private router: Router,
    private userService: UserService,
    private auth:AuthService) { }

  ngOnInit() {
    this.user =  Object.assign({}, this.auth.user);
    this.userSubs = this.userService.userChanged.subscribe((user:User)=>{
      this.user = user;
    })
  }

  ngOnDestroy(){
    this.userSubs.unsubscribe();
  }

  onCancel(){
    this.router.navigate(['home']);
  }

  onChangeProfileImage(){
    const dialogRef = this.dialog.open(ChangeImageComponent, {
      data: null
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  onSubmit(form:NgForm){
    var user = form.value;
    user.image = this.auth.user.image;
    this.userService.update(form.value);
  }
}