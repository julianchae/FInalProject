import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodTruck } from 'src/app/models/food-truck';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { FoodTruckService } from 'src/app/services/food-truck.service';

@Component({
  selector: 'app-crud-food-truck',
  templateUrl: './crud-food-truck.component.html',
  styleUrls: ['./crud-food-truck.component.css']
})
export class CrudFoodTruckComponent implements OnInit {

  foodTrucks: FoodTruck[] = [];

  selected: FoodTruck | null = null;

  newFoodTruck: FoodTruck = new FoodTruck();

  editFoodTruck: FoodTruck | null = null;



  constructor(private truckSvc: FoodTruckService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService) { }

    foodTruck: FoodTruck | null = null;
    user: User | null = null;

  ngOnInit(): void {
    this.loadFoodTruck();
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

  loadFoodTruck(){
    this.truckSvc.index().subscribe(
      success => this.foodTrucks = success,
      err => console.log("Observable got an error " + err)
    );
  }


  createFoodTruck(foodTruck: FoodTruck){
    this.truckSvc.create(foodTruck).subscribe(
      data => {
        this.loadFoodTruck();
        this.newFoodTruck = new FoodTruck();
      },
      err => console.log("Observable got an error " + err)
      );

  }

  deleteFoodTruck(id: number) {
    this.truckSvc.destroy(id).subscribe(
      data => this.reload(),
      err => console.log(err)
    );
  }
  reload() {
    this.truckSvc.index().subscribe(
      data => this.foodTrucks = data,
      err => console.log(err)
    );
  }
  updateFoodTruck(foodTruck: FoodTruck){
    this.truckSvc.update(foodTruck).subscribe(
      data => {
        this.reload();
        this.selected = null;
        this.editFoodTruck = null;
      }
    );
  }
  setEditFoodTruck(foodTruck: FoodTruck) {
    this.selected = foodTruck;
    this.editFoodTruck = Object.assign({}, this.selected);
  }
  displayWorkout(foodTruck: FoodTruck) {
    this.selected = foodTruck;
  }

  displayTable(){
    this.selected = null;
  }
}
