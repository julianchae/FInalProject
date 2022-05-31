import { FoodTruckService } from 'src/app/services/food-truck.service';
import { MenuService } from './../../services/menu.service';
import { Component, Input, OnInit } from '@angular/core';
import { Menu } from 'src/app/models/menu';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { FoodTruck } from 'src/app/models/food-truck';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  @Input() truck: FoodTruck = new FoodTruck();

  menuItems: Menu[] = [];

  selected: Menu | null = null;

  newMenuItem: Menu = new Menu();

  editMenuItem: Menu | null = null;

  userFoodTrucks: FoodTruck[] = [];

  addMenuItem: boolean = false;

  constructor(private menuServ: MenuService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private truckService: FoodTruckService) { }

    menuItem: Menu | null = null;
    user: User | null = null;


  ngOnInit(): void {
    this.loadMenu();
    this.authService.getLoggedInUser().subscribe({
      next:(user) => {
        this.user = Object.assign({},user);
        // this.user.password = '';
        //  this.user = user;
        // console.log(user);

      },
      error:(fail) => {
        console.error(fail);
        // return null;
      }
    });
  }

  loadMenu(){
    this.menuServ.index(this.truck.id).subscribe(
      success => this.menuItems = success,
      err => console.log("Observable got an error " + err)
    );
  }
  createMenuItem(menuItem: Menu){
    this.menuServ.create(menuItem, this.truck.id).subscribe(
      data => {
        this.loadMenu();
        this.newMenuItem = new Menu();
      },
      err => console.log("Observable got an error " + err)
      );

  }
  deleteMenuItem(id: number) {
    this.menuServ.destroy(id).subscribe(
      data => this.reload(),
      err => console.log(err)
    );
  }
  reload() {
    this.menuServ.index(this.truck.id).subscribe(
      data => this.menuItems = data,
      err => console.log(err)
    );
  }
  updateFoodTruck(menuItem: Menu, mid: number){
    this.menuServ.update(menuItem, mid).subscribe(
      data => {
        this.reload();
        this.selected = null;
        this.editMenuItem = null;
      }
    );
  }
  setEditMenuItem(foodTruck: Menu) {
    this.editMenuItem = Object.assign({}, foodTruck);
    console.log(this.editMenuItem);
  }
  displayMenuItem(menuItem: Menu) {
    this.selected = menuItem;
  }

  displayTable(){
    this.selected = null;
  }

  userOwnsTruck(): boolean {
    let isOwner: boolean = false;
    if (this.user?.id === this.truck.user?.id) {
      isOwner = true;
    }
    return isOwner;
  }
}


