import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FoodTruck } from 'src/app/models/food-truck';
import { FoodTruckService } from 'src/app/services/food-truck.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  selected: FoodTruck[] = [];
  searchTerm: string = "";

  constructor(private truckSvc: FoodTruckService, private router: Router) { }

  ngOnInit(): void {
  }

  show(searchTerm: string){
    this.truckSvc.searchForTrucksByKeyword(searchTerm).subscribe(
      data => {this.selected = data;
        if(!this.selected){
          this.router.navigateByUrl('/nothingFound')
        }
      }
      ,
      err => {console.log(err);
        if(!this.selected){
          this.router.navigateByUrl('/nothingFound')
        }
      }
    )

  }

}
