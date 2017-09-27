import { SimpleNamed } from './../../../shared/models/simple-named.model';
import { Image } from './../../../shared/models/image.model';

export class Room {
    
        constructor(
            public id: number, 
            public images: Image[], 
            public services: SimpleNamed[],
            public description: string,
            public capacity: number,
            public type: string){}
    }