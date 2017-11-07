import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Configuration } from '../models/configuration.model';
import { PortalResponse } from '../../../shared/models/portal-response.model';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ConfigurationService {

  configuration:Configuration = null;

  constructor(private toastr: ToastrService, private httpClient: HttpClient) { }

  getConfiguration():Promise<Configuration>{
    if (this.configuration != null) {
      return Observable.of(this.configuration).toPromise();
    } else {
      return this.httpClient.get('portal-seven-web/api/rest/configuration/')
      .map((response:PortalResponse)=>{
        if(response.success) {
          this.configuration = <Configuration>response.data;
          return this.configuration;
        } else {
          this.toastr.error(response.errorMessage);
          this.configuration = null;
          return null;
        }
      }).toPromise();
    }
  }

  saveConfiguration(config:Configuration){
    return this.httpClient.post('portal-seven-web/api/rest/configuration/', config)
      .map((response:PortalResponse)=>{
        if(response.success) {
          this.configuration = config;
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
