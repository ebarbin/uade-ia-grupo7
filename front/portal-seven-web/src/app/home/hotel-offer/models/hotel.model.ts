import { Image } from '../../../shared/models/image.model';

export class Hotel {

    constructor(
        public id: number, 
        public images: Image[], 
        public name: string){}
}