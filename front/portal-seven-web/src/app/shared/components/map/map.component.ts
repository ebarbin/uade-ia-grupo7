import { Address } from './../../../home/hotel-offer/models/address.model';
import { Component, OnInit, Input } from '@angular/core';

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
