import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'first'
})
export class FirstPipe implements PipeTransform {

  transform(value: any[], args?: any): any {
    if (value && value.length > 0){
      return value[0];
    } else {
      return null;
    }
  }

}
