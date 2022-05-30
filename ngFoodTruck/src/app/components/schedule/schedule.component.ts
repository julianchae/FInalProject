import { Component, Input, OnInit } from '@angular/core';
import { FoodTruck } from 'src/app/models/food-truck';
import { Schedule } from 'src/app/models/schedule';
import { ScheduleService } from 'src/app/services/schedule.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  @Input() truck: FoodTruck = new FoodTruck();
  schedule: Schedule[] = [];

  constructor(private scheduleService: ScheduleService) { }

  ngOnInit(): void {
    this.show(this.truck.id);
  }

  show(id: number){
    this.scheduleService.show(id).subscribe(
      success => {
        this.schedule = success;
      },
      err => {
        console.log(err);
      }
    );
  }

}
