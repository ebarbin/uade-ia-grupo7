import { PackageOfferService } from './services/package-offer.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-package',
  templateUrl: './package-offer.component.html',
  styleUrls: ['./package-offer.component.css']
})
export class PackageOfferComponent implements OnInit {

  constructor(
    private packageOfferService: PackageOfferService) { }

  ngOnInit() {
    this.packageOfferService.reset();
  }
}
