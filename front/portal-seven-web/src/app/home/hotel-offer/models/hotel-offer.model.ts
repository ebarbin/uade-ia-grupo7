import { Room } from './room.model';
import { Hotel } from './hotel.model';
import { Image } from '../../../shared/models/image.model';
import { SimpleNamed } from '../../../shared/models/simple-named.model';

export class HotelOffer {

    constructor(
        public id: number, 
        public hotel: Hotel,
        public cancellationPolicy: string,
        room: Room){}
}