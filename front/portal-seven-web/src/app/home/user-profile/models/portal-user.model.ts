import { Image } from '../../../shared/models/image.model';

export class PortalUser {

    constructor(
        public id:number,
        public userName:string,
        public sureName:string,
        public email:string,
        public firstName:string,
        public password:string,
        public image:Image){}
}