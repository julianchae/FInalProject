
import { AuthService } from 'src/app/services/auth.service';
import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { ProfileService } from 'src/app/services/profile.service';
import { environment } from 'src/environments/environment';
import { FoodTruck } from 'src/app/models/food-truck';
import { FoodTruckService } from 'src/app/services/food-truck.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  @Input() truck: FoodTruck = new FoodTruck();
  foodTrucks: FoodTruck[] = [];
  selected: FoodTruck | null = null;
  newFoodTruck: FoodTruck = new FoodTruck();
  editFoodTruck: FoodTruck | null = null;
  requests: Request | null = null;
  foodTruck: FoodTruck | null = null;
  color1 = '#7F6C79';
  // private url = environment.baseUrl + "api/profile";

  //newProfile: Profile = new Profile();
  tempUser: User = new User();
  user: User | null = null;


  constructor(private profileService: ProfileService, private authService: AuthService, private truckSvc: FoodTruckService) { }

  ngOnInit(): void {
    this.authService.getLoggedInUser().subscribe({
      next:(user) => {
        this.tempUser = Object.assign({},user);
        this.tempUser.password = '';
         this.user = user;
        console.log(user);
        // user1 = user;
        // return user;
      },
      error:(fail) => {
        console.error(fail);
        // return null;
      }
    });
  }

  updateUser(user: User, id: number | null){
    if (!id) {
      return
    }
    this.profileService.updateUser(user, id).subscribe(
      data => {
        //this.reload();
        //this.editProfile = null;
        Object.assign({}, user);
      },
      err => console.error(err)
    );

  }
  userOwnsTruck(): boolean {
    let isOwner: boolean = false;
    // for(this.user.role === foodTruckOwner)
    {

      if (this.user?.id === this.truck.user?.id) {
    }
      isOwner = true;
      return isOwner;
    }
  }

  displayFoodTrucks(foodTruck: FoodTruck) {
    this.truckSvc.getUserTrucks().subscribe(
      data =>{
        this.foodTrucks= Object.assign({}, data)
      },
      err => console.log(err)
    );
  }
  displayRequests(request: Request) {
    this.requests = request;
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
  reload() {
    this.truckSvc.index().subscribe(
      data => this.foodTrucks = data,
      err => console.log(err)
    );
  }

  }

