import { Room } from './../../models/room.model';
import { HotelOfferService } from './../../services/hotel-offer.service';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-hotel-offer-detail-room',
  templateUrl: './hotel-offer-detail-room.component.html',
  styleUrls: ['./hotel-offer-detail-room.component.css']
})
export class HotelOfferDetailRoomComponent {

  @Input() room: Room;
  
  constructor() { }

}
