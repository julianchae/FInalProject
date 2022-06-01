import { AuthService } from './../../services/auth.service';
import { Router } from '@angular/router';
import { FoodTruck } from './../../models/food-truck';
import { FoodTruckService } from './../../services/food-truck.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';

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

  showMenuItems: boolean = true;
  showAboutUs: boolean = false;
  showSchedule: boolean = false;
  showComments: boolean = false;
  user: User | null = null;

  constructor(private truckSvc: FoodTruckService,
            private route: ActivatedRoute,
            private authService: AuthService,
            private router: Router
            ) { }

  ngOnInit(): void {
    if (this.route.snapshot.paramMap.get('id')) {
      let id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.displaySingleTruck(parseInt(id));
      }
    }
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

  selectDisplayDiv(choice: string) {
    if (choice === 'menu') {
      this.showMenuItems = true;
      this.showAboutUs = false;
      this.showComments = false;
      this.showSchedule = false;
    } else if (choice === 'about') {
      this.showMenuItems = false;
      this.showAboutUs = true;
      this.showComments = false;
      this.showSchedule = false;
    } else if (choice === 'schedule') {
      this.showMenuItems = false;
      this.showAboutUs = false;
      this.showComments = false;
      this.showSchedule = true;
    } else if (choice === 'comment') {
      this.showMenuItems = false;
      this.showAboutUs = false;
      this.showComments = true;
      this.showSchedule = false;
    }

  }

  // loadFoodTruck(){
  //   this.truckSvc.index().subscribe(
  //     success => this.foodTrucks = success,
  //     err => console.log("Observable got an error " + err)
  //   );
  // }


}
