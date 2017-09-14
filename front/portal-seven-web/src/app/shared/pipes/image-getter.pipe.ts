import { Pipe, PipeTransform } from '@angular/core';

import { Image } from '../models/image.model';

@Pipe({
  name: 'imageGetter'
})
export class ImageGetterPipe implements PipeTransform {

  transform(image: Image, args?: any): any {
    if (image) {
      return 'portal-seven-web/api/rest/image/' + image.id;
    } else {
      return 'assets/images/no-image.png';
    }
  }

}
