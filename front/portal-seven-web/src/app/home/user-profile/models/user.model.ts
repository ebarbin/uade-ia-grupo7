import { Image } from './image.model';

export class User {

    constructor(
        public id:number,
        public userName:string,
        public sureName:string,
        public email:string,
        public firstName:string,
        public password:string,
        public image:Image){}
}