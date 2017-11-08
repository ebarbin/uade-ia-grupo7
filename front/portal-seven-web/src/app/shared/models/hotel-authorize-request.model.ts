import { PortalUser } from './../../home/user-profile/models/portal-user.model';
export class HotelAuthorizeRequest {

    
    public roomQuantity: number;
    public fromDate: Date;
    public toDate: Date;
    public portalUser: PortalUser;

    constructor(){}
}