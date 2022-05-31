import { Location } from './location';
export class Request {

  id:number;
  remarks: string | null;
  requestPlaced: string |null;
  requestedDate : string | null;
  accepted : boolean;
  location : Location | null;

  constructor(

    id:number= 0,
    remarks: string | null ='',
    requestPlaced: string |null = '',
    requestedDate : string | null ='',
    accepted : boolean = false,
    location : Location | null = null

  ){

    this.id = id;
    this.remarks = remarks;
    this.requestPlaced = requestPlaced;
    this.requestedDate = requestedDate;
    this.accepted = accepted;
    this.location = location;
  }

}
