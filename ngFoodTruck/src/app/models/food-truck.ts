import { User } from 'src/app/models/user';
export class FoodTruck {

  id: number;
  name: string | null;
  description: string | null;
  imgUrl: string | null ;
  active: boolean;
  dateCreated: string | null;
  websiteUrl: string | null;
  user: User | null;

  constructor(
    id: number = 0,
    name: string | null ='',
    description: string | null ='',
    imgUrl: string | null ='' ,
    active: boolean = true,
    dateCreated: string | null ='',
    websiteUrl: string | null ='',
    user: User | null = null

  ) {

    this.id = id;
    this.name = name;
    this.description = description;
    this.imgUrl = imgUrl;
    this.active = active;
    this.dateCreated = dateCreated;
    this.websiteUrl = websiteUrl;
    this. user = user;
  }
}
