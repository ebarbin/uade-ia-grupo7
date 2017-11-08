import { PackageOfferService } from './../../services/package-offer.service';
import { Router } from '@angular/router';
import { Component } from '@angular/core';

@Component({
  selector: 'app-package-offer-reservation-resume',
  templateUrl: './package-offer-reservation-resume.component.html',
  styleUrls: ['./package-offer-reservation-resume.component.css']
})
export class PackageOfferReservationResumeComponent {

  constructor(
    public packageOfferService:PackageOfferService,
    private router:Router) { }

  back(){
    this.router.navigate(['home/package-offer']);
  }

}
