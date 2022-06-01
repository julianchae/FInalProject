import { Request } from './../../models/request';
import { RequestService } from './../../services/request.service';

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

  foodTrucks: FoodTruck[] = [];
  selected: FoodTruck | null = null;
  newFoodTruck: FoodTruck = new FoodTruck();
  editFoodTruck: FoodTruck | null = null;
  userRequests: Request[] = [];
  truckRequests: Request[] = []
  foodTruck: FoodTruck | null = null;
  color1 = '#7F6C79';
  displayedColumns: string[] = ['name', 'description', 'id'];
  reqColumn: string[] = ['id', 'remarks', 'username'];
  // private url = environment.baseUrl + "api/profile";

  //newProfile: Profile = new Profile();
  tempUser: User | null = null
  user: User | null = null;


  constructor(
    private profileService: ProfileService,
    private authService: AuthService,
    private truckSvc: FoodTruckService,
    private requestSvc: RequestService) { }

  ngOnInit(): void {
    this.authService.getLoggedInUser().subscribe({
      next:(user) => {
         this.user = user;
        console.log(user);
        this.loadUserTrucks();
        this.getUsersRequests();
        // user1 = user;
        // return user;
      },
      error:(fail) => {
        console.error(fail);
        // return null;
      }
    });

    console.log(this.truckRequests)
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

  setTempUser() {
    if(this.user){
    this.tempUser = this.user;
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


  getTruckRequests(id: number) {
    this.requestSvc.getRequestsOnTruck(id).subscribe(
      data => {
        this.truckRequests.push(...data);
        console.log(this.truckRequests);
      },
      err => {
        console.log(err);
      }
    )
  }

  getAllUserTruckRequests() {
    console.log("Inside getAllUserTruckReq()");
    console.log(this.foodTrucks);
    for(let truck of this.foodTrucks) {
      this.getTruckRequests(truck.id);
    }
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

  loadUserTrucks() {
    this.truckSvc.getUserTrucks().subscribe(
      data => {
        console.log("inside load user trucks");
        this.foodTrucks = data;
        this.getAllUserTruckRequests();
      },
      err => {
        console.log(err);
      }
    )
  }

  updateRequest(req: Request, id: number, accept: boolean) {
    if (accept) {
      req.accepted = true;
    } else {
      req.accepted = false;
    }

    this.requestSvc.updateRequest(req, id).subscribe(
      data => {
      },
      err => {
        console.log(err);
      }
    )
  }

  getUsersRequests(){
    this.requestSvc.getUserRequests().subscribe(
      data => {
        this.userRequests = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  }

