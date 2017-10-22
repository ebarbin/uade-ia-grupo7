import { FavouriteOfferService } from './../../services/favourite-offer.service';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-favourite-hotel-reservation-resume',
  templateUrl: './favourite-hotel-reservation-resume.component.html',
  styleUrls: ['./favourite-hotel-reservation-resume.component.css']
})
export class FavouriteHotelReservationResumeComponent implements OnInit {

  constructor(private service:FavouriteOfferService) { }

  ngOnInit() {
  }

}
