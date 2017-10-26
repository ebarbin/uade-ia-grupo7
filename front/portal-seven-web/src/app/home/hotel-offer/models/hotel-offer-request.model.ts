import { AutocompleteResource } from './../../../shared/models/autocomplete-resource.model';

export class HotelOfferRequest {
    
    constructor(
        public toDate: Date,
        public fromDate:Date,
        public hotel: AutocompleteResource,
        public peoplePerRoom: number,
        public roomQuantity: number,
        public minPrice: number,
        public maxPrice: number){}
}