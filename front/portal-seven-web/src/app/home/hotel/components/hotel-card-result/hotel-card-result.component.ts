import { Component, OnInit, Input } from '@angular/core';

import { Hotel } from '../../models/hotel.model';

@Component({
  selector: 'app-hotel-card-result',
  templateUrl: './hotel-card-result.component.html',
  styleUrls: ['./hotel-card-result.component.css']
})
export class HotelCardResultComponent implements OnInit {

  @Input()hotels:Hotel[];

  constructor() { }

  ngOnInit() {}

}
