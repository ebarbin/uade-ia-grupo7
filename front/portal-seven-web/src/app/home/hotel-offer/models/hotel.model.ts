import { SimpleNamed } from './../../../shared/models/simple-named.model';
import { Room } from './room.model';
import { Address } from './address.model';
import { Image } from '../../../shared/models/image.model';

export class Hotel {

    constructor(
        public id: number, 
        public images: Image[], 
        public name: string,
        public description: string,
        public address:Address,
        public services: SimpleNamed[],
        public paymentMethods: SimpleNamed[]){}
}