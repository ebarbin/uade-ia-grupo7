import { Component, OnInit, ElementRef } from '@angular/core';
import { PortalUser } from '../../models/portal-user.model';
import { NgForm } from '@angular/forms';
import { NewPortalUser } from '../../models/new-portal-user.modal';
import { UserService } from '../../services/user.service';
import { PortalResponse } from '../../../../shared/models/portal-response.model';
import { MdDialogRef } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { ImageService } from '../../services/image.service';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  constructor(
    private toastr: ToastrService,
    private dialogRef: MdDialogRef<NewUserComponent>,
    private elem: ElementRef, 
    private userService: UserService,
    private imageService: ImageService) { }

  ngOnInit() {
  }
  
  onSubmit(form:NgForm){
    var user = <PortalUser>form.value;
    let imageData = null;
    let files = this.elem.nativeElement.querySelector('#uploadFile').files;
    
    let file = files[0];
    if (file) {
      imageData = new FormData();
      imageData.append('uploadFile', file, file.name);
    }
    
    this.userService.addNewUser(user).then((response:PortalResponse) => {
      if (response.success) {
        var userPortal = <PortalUser>response.data;
        if (imageData) {
          this.imageService.uploadImage(userPortal, imageData)
          .then((response:PortalResponse) => {
            if (response.success) {
              this.toastr.success('Usuario creado con éxito.');
              this.dialogRef.close();
            } else {
                this.toastr.error(response.errorMessage);
            }
          }).catch((res:HttpErrorResponse) => {
            this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
          });
        } else {
          this.toastr.success('Usuario creado con éxito.');
          this.dialogRef.close();
        }
      } else {
        this.toastr.error(response.errorMessage);
      }
    }).catch((res:HttpErrorResponse) => {
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }
  
}
