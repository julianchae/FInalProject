import { Schedule } from 'src/app/models/schedule';
export class Location {

  id: number;
  comment : string | null;
  street: string | null;
   city : string | null;
   state: string | null;
   zip: string | null;
   schedules : Schedule[] | null;
   requests : Request[] | null;


   constructor(
    id: number = 0,
    comment : string | null ='',
    street: string | null = '',
     city : string | null = '',
     state: string | null = '',
     zip: string | null = ' ',
     schedules : Schedule[]| null = null,
     requests: Request[] | null = null



   ){
     this.id = id;
     this.comment = comment;
     this.street = street;
     this.city = city;
     this.state = state;
     this.zip = zip;
     this.schedules = schedules;
     this.requests = requests;
   }
}
