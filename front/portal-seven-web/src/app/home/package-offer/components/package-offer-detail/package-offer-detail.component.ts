import { PackageOfferHeader } from './../../models/package-offer-header.model';
import { MD_DIALOG_DATA, MdDialogRef } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-package-offer-detail',
  templateUrl: './package-offer-detail.component.html',
  styleUrls: ['./package-offer-detail.component.css']
})
export class PackageOfferDetailComponent implements OnInit {

  constructor(public dialogRef: MdDialogRef<PackageOfferDetailComponent>,
    @Inject(MD_DIALOG_DATA) public packageOffer: PackageOfferHeader) { 
      console.log(packageOffer);
    }

  ngOnInit() {
  }

}
