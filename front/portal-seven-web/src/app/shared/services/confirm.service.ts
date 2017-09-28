import { Injectable } from '@angular/core';
import { MdDialog } from '@angular/material';
import { ConfirmComponent } from '../components/confirm/confirm.component';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DialogService {

  constructor(private dialog: MdDialog) { }

  confirm(title:string, message:string): Observable<any>{
    const dialogRef = this.dialog.open(ConfirmComponent, {
      data: {title: title, message: message}
    });
    return dialogRef.afterClosed();
  }
}
