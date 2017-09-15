import { Component, Inject, OnInit } from '@angular/core';
import {MdDialogRef, MD_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrls: ['./hotel-detail.component.css']
})
export class HotelDetailComponent implements OnInit {

  constructor(public dialogRef: MdDialogRef<HotelDetailComponent>,
    @Inject(MD_DIALOG_DATA) public data: any) { 
      console.log(data);
    }

  ngOnInit() {
  }

}
