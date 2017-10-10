import { SimpleNamed } from './../../../shared/models/simple-named.model';
import { Room } from './room.model';
import { Image } from '../../../shared/models/image.model';
import { Address } from '../../../shared/models/address.model';

export class Hotel {

    constructor(
        public id: number, 
        public images: Image[], 
        public name: string,
        public description: string,
        public address:Address,
        public rooms: Room[],
        public services: SimpleNamed[],
        public paymentMethods: SimpleNamed[]){}
}