export class Menu {


  id: number;
  name: string | null;
  description: string | null;
  imgUrl: string | null ;
  active: boolean;
  price: number | null;


  constructor(
    id: number = 0,
    name: string | null ='',
    description: string | null ='',
    imgUrl: string | null ='' ,
    active: boolean = true,
    price: number | null = 0


  ) {

    this.id = id;
    this.name = name;
    this.description = description;
    this.imgUrl = imgUrl;
    this.active = active;
    this.price = price;
  }
}

