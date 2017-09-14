import { Image } from './image.model';

export class User {

    constructor(
        public userName:string,
        public firstName:string,
        public password:string,
        public image:Image){}
}