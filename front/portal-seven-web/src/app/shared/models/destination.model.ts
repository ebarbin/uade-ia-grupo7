import { Address } from './address.model';
export class Destination {
    
        constructor(
            public id: number,
            public name: string,
            public address: Address){}
    }