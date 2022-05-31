import { LocationService } from './../../services/location.service';
import { Request } from './../../models/request';
import { RequestService } from './../../services/request.service';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodTruck } from 'src/app/models/food-truck';
import { AuthService } from 'src/app/services/auth.service';
import { Location } from 'src/app/models/location';
import { FoodTruckService } from 'src/app/services/food-truck.service';


@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {
  truck: FoodTruck = new FoodTruck();
  requests : Request [] = [];
  newRequest: Request = new Request();
  request: Request = new Request();
  newLocation: Location = new Location();
  location: Location = new Location();

  constructor(private requestSvc: RequestService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private locationService: LocationService,
    private truckServe: FoodTruckService) { }

  ngOnInit(): void {


if(this.route.snapshot.paramMap.get('tid')){
  let id = this.route.snapshot.paramMap.get('tid');
  if(id){


this.findTruckById(parseInt(id));

  }
  this.loadRequestsForTruck();
}

  }
  findTruckById(id:number){

return this.truckServe.getSingleTruck(id).subscribe(
  success =>{this.truck = success
console.log(this.truck)
  } ,
  err => console.log("Observable got an error " + err)
);

  }

loadRequestsForTruck(){

this.requestSvc.getRequestsOnTruck(this.truck.id).subscribe(
  success => this.requests = success,
  err => console.log("Observable got an error " + err)
);

}


addLocationToRequest(request: Request, location: Location) {
  if (
    !location.street ||
    !location.state ||
    !location.city ||
    !location.zip
  ) {
    return;
  }
  this.locationService.findByStreet(location.street).subscribe(
    (data) => {
      this.newLocation = data;
      request.location = this.newLocation;
      this.createRequestForTruck(request, this.newLocation);

      console.log(data);
    },
    (err) => {
      this.locationService.createLocation(location).subscribe(
        (data) => {
          this.newLocation = data;
          request.location = this.newLocation;
          this.createRequestForTruck(request, this.newLocation);

          console.log(data);
        },
        (err) => {
          console.log(err);
        }
      );
    }
  );
}

createRequestForTruck(request: Request, location: Location){
  this.requestSvc.userCanCreateRequest(this.truck.id,
    location.id, this.request ).subscribe(
      data => {
        this.loadRequestsForTruck();
        this.newRequest = new Request();
        this.router.navigateByUrl('/home');
      },
      err => console.log("Observable got an error " + err)
      );
}

}
