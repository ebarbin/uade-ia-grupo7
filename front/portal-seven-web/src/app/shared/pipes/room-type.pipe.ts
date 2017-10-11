import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'roomType'
})
export class RoomTypePipe implements PipeTransform {

  transform(value: number, args?: any): any {
    if (!value) return '<vacio>';
    if (value == 1) return 'Simple';
    else if (value == 2) return 'Doble';
    else if (value == 3) return 'Triple';
    else if (value == 4) return 'Cuádruple';
    else if (value == 5) return 'Quíntuple';
    else if (value == 6) return 'Séxtuple';
    else if (value == 7) return 'Séptuple';
    else if (value == 8) return 'Óctuple ';
    else if (value == 9) return 'Nónuple  ';
    else if (value == 10) return 'Décuple ';
  }

}
