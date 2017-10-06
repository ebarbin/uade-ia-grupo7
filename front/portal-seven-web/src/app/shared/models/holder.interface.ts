export interface Holder {

    sortResults(sortDirection:string, sortField:string);

    setView(view:string);

    getView():string;

    getType():string;

    getSortValues():any[];
}