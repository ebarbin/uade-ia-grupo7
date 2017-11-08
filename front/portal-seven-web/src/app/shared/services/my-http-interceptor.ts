import {Injectable} from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { BlockUI, NgBlockUI } from 'ng-block-ui';

@Injectable()
export class MyHttpInterceptor implements HttpInterceptor {

  @BlockUI() blockUI: NgBlockUI;

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.blockUI.start('Cargando...');
    return next.handle(req).timeout(10000).finally(()=>{
      this.blockUI.stop();
    });
  }
}