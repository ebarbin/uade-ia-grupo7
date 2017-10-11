
import { Component, OnInit, Input } from '@angular/core';
import { Address } from '../../models/address.model';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  @Input()address: Address;
  
  constructor() { }

  ngOnInit() {
  }

}
