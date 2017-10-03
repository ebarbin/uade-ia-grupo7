import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';

import { ToastrService } from 'ngx-toastr';
import { MdDialog } from '@angular/material';

import { AuthService } from '../../auth/services/auth.service';
import { UserService } from './services/user.service';
import { ImageService } from './services/image.service';

import { PortalUser } from './models/portal-user.model';
import { PortalResponse } from './../../shared/models/portal-response.model';

import { NewUserComponent } from './components/new-user/new-user.component';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit, OnDestroy {

  userSubs: Subscription;

  user: PortalUser;

  constructor(
    private toastr:ToastrService,
    private elem: ElementRef,
    private dialog: MdDialog,
    private router: Router,
    private userService: UserService,
    private imageService:ImageService,
    private auth:AuthService) { }

  ngOnInit() {
    this.user =  Object.assign({}, this.auth.getUser());
    this.userSubs = this.userService.userChanged.subscribe((user:PortalUser)=>{
      this.user = Object.assign({}, user);
    })
  }

  ngOnDestroy(){
    this.userSubs.unsubscribe();
  }

  onCancel(){
    this.router.navigate(['home']);
  }

  onNewUser(){
    const dialogRef = this.dialog.open(NewUserComponent, {
      width: '500px',
      data: null
    });
  
    dialogRef.afterClosed().subscribe(result => {});
  }

  onSubmit(form:NgForm){
    var user = form.value;
    user.image = this.auth.getUser().image;

    let imageData = null;
    let files = this.elem.nativeElement.querySelector('#uploadFile').files;
    
    let file = files[0];
    if (file) {
      imageData = new FormData();
      imageData.append('uploadFile', file, file.name);
    }

    this.userService.update(form.value).then((response:PortalResponse) => {
      if (response.success) {
        var userPortal = <PortalUser>response.data;
        if (imageData) {
          this.imageService.uploadImage(userPortal, imageData)
          .then((response:PortalResponse) => {
            if (response.success) {
              this.userService.userChanged.next(<PortalUser>response.data);
              this.toastr.success('Usuario creado con éxito.');
            } else {
                this.toastr.error(response.errorMessage);
            }
          }).catch((res:HttpErrorResponse) => {
            this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
          });
        } else {
          this.userService.userChanged.next(userPortal);
          this.toastr.success('Usuario actualizado con éxito.');
        }
      } else {
        this.toastr.error(response.errorMessage);
      }
    }).catch((res:HttpErrorResponse) => {
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }
}
