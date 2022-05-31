import { FoodTruck } from './../../models/food-truck';
import { Component, OnInit } from '@angular/core';
import { FoodTruckService } from 'src/app/services/food-truck.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private truckSvc: FoodTruckService, private router: Router) { }

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
