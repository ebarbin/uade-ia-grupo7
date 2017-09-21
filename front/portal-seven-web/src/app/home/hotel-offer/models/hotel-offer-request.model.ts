import { HotelAutocomplete } from '../components/hotel-offer-filter/models/hotel-autocomplete.model';

export class HotelOfferRequest {
    
    constructor(
        public toDate: Date,
        public fromDate:Date,
        public hotel: HotelAutocomplete,
        public peoplePerRoom: number,
        public roomQuantity: number){}
}