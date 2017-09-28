import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable()
export class ErrorHandlerService {

  constructor(private toastr: ToastrService) { }

  private isJson(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
  }

  set(res:HttpErrorResponse) {
    if (res.status == 504) {
      this.toastr.error('El servidor no responde. Verifique el estado del mismo.');
    } else {
      try {
        var error = JSON.parse(res.error);
        this.toastr.error(error.errorMessage);
      } catch (e) {
        this.toastr.error(res.error);
      }
    }
  }
}
