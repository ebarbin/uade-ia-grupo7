import { Image } from '../../../shared/models/image.model';
import { SimpleNamed } from '../../../shared/models/simple-named.model';

export class PackageOfferHeader {

    constructor(
        public id: number, 
        public description: string, 
        public images: Image[], 
        public price: number,
        public offerStart: Date,
        public offerEnd: Date,
        public services: SimpleNamed[],
        public favourite:boolean){}
}