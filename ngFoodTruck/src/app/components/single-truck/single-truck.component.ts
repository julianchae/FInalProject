import { Router } from '@angular/router';
import { FoodTruck } from './../../models/food-truck';
import { FoodTruckService } from './../../services/food-truck.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-single-truck',

  templateUrl: './single-truck.component.html',
  styleUrls: ['./single-truck.component.css']
})
export class SingleTruckComponent implements OnInit {

  foodTrucks: FoodTruck[] = [];

  selected: FoodTruck | null = null;

  newFoodTruck: FoodTruck = new FoodTruck();

  editFoodTruck: FoodTruck | null = null;


  constructor(private truckSvc: FoodTruckService,
            private route: ActivatedRoute,
            private router: Router
            ) { }

  ngOnInit(): void {
    if (this.route.snapshot.paramMap.get('id')) {
      let id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.displaySingleTruck(parseInt(id));
      }
    }
  }

  foodTruck: FoodTruck | null = null;

  displaySingleTruck(id: number ) {
    this.truckSvc.getSingleTruck(id).subscribe(
      success => {
        this.foodTruck = success;
        this.router.navigateByUrl('/truck/' + id);
      },
      err => {
         console.log('Ovservable got and error' + err)
         this.router.navigateByUrl('/notFound' );
      }
    );
  }
  // loadFoodTruck(){
  //   this.truckSvc.index().subscribe(
  //     success => this.foodTrucks = success,
  //     err => console.log("Observable got an error " + err)
  //   );
  // }


}
