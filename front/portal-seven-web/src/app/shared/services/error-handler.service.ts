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
        var error = res.error;
        if(error.errorMessage) {
          this.toastr.error(error.errorMessage);
        } else {
          this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
        }
      } catch (e) {
        this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
      }
    }
  }
}
