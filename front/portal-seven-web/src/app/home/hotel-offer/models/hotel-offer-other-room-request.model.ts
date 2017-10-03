import { Hotel } from './hotel.model';
import { HotelOffer } from './hotel-offer.model';
import { Room } from './room.model';
export class HotelOfferOtherRoomsRequest {
    
    
    public toDate: Date;
    public fromDate:Date;
    public room: Room;
    public hotel: Hotel;

    constructor(hotelOffer: HotelOffer){
        this.hotel = hotelOffer.hotel;
        this.room = hotelOffer.room;
    }
}