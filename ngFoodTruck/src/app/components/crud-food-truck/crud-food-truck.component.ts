import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodTruck } from 'src/app/models/food-truck';
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
    private router: Router) { }

    foodTruck: FoodTruck | null = null;

  ngOnInit(): void {
    this.loadFoodTruck();
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
