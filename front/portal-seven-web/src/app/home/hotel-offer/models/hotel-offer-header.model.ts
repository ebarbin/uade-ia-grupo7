import { Image } from '../../../shared/models/image.model';
import { SimpleNamed } from '../../../shared/models/simple-named.model';

export class HotelOfferHeader {

    constructor(
        public id: number, 
        public name: string, 
        public images: Image[], 
        public description: string,
        public price: number,
        public roomCapacity: number,
        public offerStart: Date,
        public offerEnd: Date,
        public services: SimpleNamed[],
        public favorite: boolean){}
}