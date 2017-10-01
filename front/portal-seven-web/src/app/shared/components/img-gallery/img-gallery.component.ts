import { Component, OnInit, Input } from '@angular/core';
import { NgxGalleryOptions, NgxGalleryImage, NgxGalleryAnimation } from 'ngx-gallery';
import { Image } from '../../models/image.model';
import { ImageGetterPipe } from '../../pipes/image-getter.pipe';
import { HotelOffer } from '../../../home/hotel-offer/models/hotel-offer.model';

@Component({
  selector: 'app-img-gallery',
  templateUrl: './img-gallery.component.html',
  styleUrls: ['./img-gallery.component.css']
})
export class ImgGalleryComponent implements OnInit {

  @Input()images:Image[] = [];

  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[];

  constructor() { }

  ngOnInit(): void {     
    
    this.galleryOptions = [{ 
      image: false, 
      height: "25vh",
      width: "50vw", 
      thumbnailsColumns: 4, 
      imageAnimation: NgxGalleryAnimation.Slide},
    ];
    
    this.galleryImages = [];
    this.images.forEach((img:Image)=>{
      this.galleryImages.push({
        small: new ImageGetterPipe().transform(img),
        medium: new ImageGetterPipe().transform(img),
        big: new ImageGetterPipe().transform(img)
      })
    });
   
  }
  
}
