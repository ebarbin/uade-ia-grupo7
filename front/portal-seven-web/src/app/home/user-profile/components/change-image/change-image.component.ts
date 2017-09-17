import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';

import { MdDialogRef } from '@angular/material';

import { ImageService } from '../../services/image.service';
import { AuthService } from '../../../../auth/services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { PortalResponse } from '../../../../shared/models/portal-response.model';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-change-image',
  templateUrl: './change-image.component.html',
  styleUrls: ['./change-image.component.css']
})
export class ChangeImageComponent implements OnInit {

  constructor(
    private userService: UserService,
    private dialogRef: MdDialogRef<ChangeImageComponent>,
    private toastr:ToastrService,
    private authService:AuthService, 
    private elem: ElementRef,
    private imageService: ImageService) { }

  ngOnInit() {}

  public uploadImage(): void {
    let files = this.elem.nativeElement.querySelector('#uploadFile').files;
    let formData = new FormData();
    let file = files[0];
    if (file) {
      formData.append('uploadFile', file, file.name);
      this.imageService.uploadImage(this.authService.user, formData)
        .then((response:PortalResponse) => {
          if (response.success) {
            this.userService.userChanged.next(<User>response.data);
            this.toastr.success('Imagen actualizada exitosamente.');
            this.dialogRef.close();
          } else {
              this.toastr.error(response.errorMessage);
          }
        }).catch((res:HttpErrorResponse) => {
          if (res.error){
              this.toastr.error(res.error.errorMessage);
          } else {
              this.toastr.error(res.message);
          }
        });
    } else {
      this.toastr.error('Debe seleccionar una imagen.');
    }
  }
}
