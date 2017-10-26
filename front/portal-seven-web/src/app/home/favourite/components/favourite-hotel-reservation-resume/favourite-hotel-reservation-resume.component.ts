import { Router } from '@angular/router';
import { FavouriteOfferService } from './../../services/favourite-offer.service';

import { Component } from '@angular/core';

@Component({
  selector: 'app-favourite-hotel-reservation-resume',
  templateUrl: './favourite-hotel-reservation-resume.component.html',
  styleUrls: ['./favourite-hotel-reservation-resume.component.css']
})
export class FavouriteHotelReservationResumeComponent {

  constructor(
    public service: FavouriteOfferService,
    private router: Router) { }

  onAccept(){
    this.router.navigate(['/home']);
  }
}
