import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-sort',
  templateUrl: './sort.component.html',
  styleUrls: ['./sort.component.css']
})
export class SortComponent implements OnInit {

  @Input()type:string;
  @Input()active:boolean;
  @Output()directionChange:EventEmitter<string> = new EventEmitter();
  @Output()sortFieldChange:EventEmitter<string> = new EventEmitter();

  sortFieldSelected:string = '';

  direction:string = 'desc';

  toogleDirection() {
    if(this.direction == 'asc') this.direction = 'desc';
    else this.direction = 'asc';
    this.directionChange.next(this.direction);
  }

  onSortChange(){
    this.direction = 'desc'
    this.directionChange.next(this.direction);
    this.sortFieldChange.next(this.sortFieldSelected);
  }

  getSortValues() {
    if (this.type == 'hotel') {
      return [
        {value:'', viewValue:''},
        {value:'name', viewValue:'Nombre Hotel'},
        {value:'description', viewValue:'Descripción'},
        {value:'price', viewValue:'Precio'},
        {value:'roomCapacity', viewValue:'Capacidad'},
        {value:'offerStart', viewValue:'Desde'},
        {value:'offerEnd', viewValue:'Hasta'}
      ];
    } 
  }
  constructor() { }

  ngOnInit() {
  }

}
