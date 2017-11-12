import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Configuration } from '../models/configuration.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ConfigurationService {

  constructor(private router:Router, private toastr: ToastrService, private httpClient: HttpClient) { }

  getConfiguration():Promise<Configuration>{
      return this.httpClient.get('portal-seven-web/api/rest/configuration/')
      .map((response:PortalResponse)=>{
        if(response.success) {
          return <Configuration>response.data;
        } else {
          this.toastr.error(response.errorMessage);
          return null;
        }
      }).toPromise();
    
  }

  saveConfiguration(config:Configuration){
    return this.httpClient.post('portal-seven-web/api/rest/configuration/', config)
      .map((response:PortalResponse)=>{
        if(response.success) {
          return null;
        } else {
          this.toastr.error(response.errorMessage);
          return null;
        }
      }).toPromise().then(() => {
        this.toastr.success('Configuración actualizada con éxito.');
    }).catch((res:HttpErrorResponse) => {
      this.toastr.error('Ha ocurrido un error. Contacte a un administrador.');
    });
  }
}
