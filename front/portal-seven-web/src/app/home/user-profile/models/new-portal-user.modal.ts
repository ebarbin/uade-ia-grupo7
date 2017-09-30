import { Image } from '../../../shared/models/image.model';
import { PortalUser } from './portal-user.model';

export class NewPortalUser {

    constructor(
        public portalUser:PortalUser,
        public imageData:FormData){}
}