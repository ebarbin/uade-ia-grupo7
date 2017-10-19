import { AutocompleteResource } from './../../../shared/models/autocomplete-resource.model';

export class PackageOfferRequest {
    
    constructor(
        public toDate: Date,
        public fromDate:Date,
        public destination: AutocompleteResource,
        public quantityPeople: number,
        public minPrice:number,
        public maxPrice:number){}
}