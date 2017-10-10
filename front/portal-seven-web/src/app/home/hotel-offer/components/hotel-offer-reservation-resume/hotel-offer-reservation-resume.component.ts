import { Component } from '@angular/core';
import { HotelOfferService } from '../../services/hotel-offer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hotel-offer-reservation-resume',
  templateUrl: './hotel-offer-reservation-resume.component.html',
  styleUrls: ['./hotel-offer-reservation-resume.component.css']
})
export class HotelOfferReservationResumeComponent {

  constructor(
    public hotelOfferService:HotelOfferService,
    private router:Router) { }

  back(){
    this.router.navigate(['home/hotel-offer']);
  }
}
