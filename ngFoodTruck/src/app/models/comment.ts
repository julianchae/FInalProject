import { FoodTruck } from "./food-truck";
import { User } from "./user";

export class Comment {

  id: number;
  comment : string | null;
  rating: number;
  commentDate: string|null;
  user : User | null;
  foodTruck : FoodTruck |null;

  constructor(

    id: number =0,
    comment: string | null ='',
    rating : number =0,
    commentDate : string | null='',
    user: User | null = null,
    foodTruck : FoodTruck | null
  ){



    this. id = id;
    this. comment = comment;
    this.rating = rating;
    this.commentDate = commentDate;
    this.user = user;
    this.foodTruck= foodTruck;
  }

}
