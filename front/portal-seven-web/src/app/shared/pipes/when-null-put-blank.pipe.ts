import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'whenNullPutBlank'
})
export class WhenNullPutBlankPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if (!value) return "";
    else return value;
  }

}
