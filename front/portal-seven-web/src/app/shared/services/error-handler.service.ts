import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable()
export class ErrorHandlerService {

  constructor(private toastr: ToastrService) { }

  set(res:HttpErrorResponse) {
    if (res.error){
      if (typeof res.error != 'object') {
        this.toastr.error(JSON.parse(res.error).errorMessage)
      } else {
        this.toastr.error(res.error.errorMessage)
      }            
    } else {
      this.toastr.error(res.message);
    }
  }

}
