import { Image } from '../../../shared/models/image.model';
import { SimpleNamed } from '../../../shared/models/simple-named.model';
import { Destination } from '../../../shared/models/destination.model';

export class PackageOffer {

    constructor(
        public id:number,
        public images:Image[],
        public price:number,
        public description:string,
        public cancellationPolicy:string,
        public services:SimpleNamed[],
        public paymentMethods:SimpleNamed[],
        public destination:Destination){}
}