import { Pipe, PipeTransform } from '@angular/core';
import { SimpleNamed } from '../models/simple-named.model';

@Pipe({
  name: 'join'
})
export class JoinPipe implements PipeTransform {

  transform(services: SimpleNamed[], args: any): any {
    return services.map((service:SimpleNamed)=>{
      return service.name;
    }).join(args);
  }

}
