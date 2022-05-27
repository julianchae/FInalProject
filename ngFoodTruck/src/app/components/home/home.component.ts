import { FoodTruck } from './../../models/food-truck';
import { FoodTruckService } from './../../services/food-truck.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private truckSvc: FoodTruckService) { }

  ngOnInit(): void {
    this.loadTrucks();
  }

  foodTrucks: FoodTruck[] = [];

  loadTrucks() {
    this.truckSvc.index().subscribe(
      success => this.foodTrucks = success,
      err => console.log('Ovservable got and error' + err)
    );
  }

}
