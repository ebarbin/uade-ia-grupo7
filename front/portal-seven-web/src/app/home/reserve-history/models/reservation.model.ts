import { Image } from '../../../shared/models/image.model';
import { SimpleNamed } from '../../../shared/models/simple-named.model';

export class Reservation {
    
    constructor(         
        public name: string, 
        public image: Image,
        public valoration: number,
        public start: Date,
        public end: Date,
        public type: string){}
}