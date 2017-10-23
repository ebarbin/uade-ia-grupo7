export class HotelAuthorizeRequest {
    constructor(
        public roomQuantity: number,
        public fromDate: Date,
        public toDate: Date){}
}