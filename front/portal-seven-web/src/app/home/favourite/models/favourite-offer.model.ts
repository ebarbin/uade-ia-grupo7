import { Destination } from './../../../shared/models/destination.model';
import { Image } from '../../../shared/models/image.model';
import { SimpleNamed } from '../../../shared/models/simple-named.model';

export class FavouriteOffer {

    constructor(
        public id: number, 
        public name: string, 
        public images: Image[], 
        public description: string,
        public price: number,
        public quantityCapacity: number,
        public offerStart: Date,
        public offerEnd: Date,
        public services: SimpleNamed[],
        public paymentMethods: SimpleNamed[],
        public favourite: boolean,
        public type: string,
        public valoration:number,
        public destination:Destination){}
}