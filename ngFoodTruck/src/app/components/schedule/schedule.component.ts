import { Component, Input, OnInit } from '@angular/core';
import { FoodTruck } from 'src/app/models/food-truck';
import { Schedule } from 'src/app/models/schedule';
import { AuthService } from 'src/app/services/auth.service';
import { ScheduleService } from 'src/app/services/schedule.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  @Input() truck: FoodTruck = new FoodTruck();
  schedule: Schedule[] = [];

  selected: Schedule | null = null;

  newSchedule: Schedule = new Schedule();
  constructor(private scheduleService: ScheduleService,
    private authService: AuthService) { }

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

  createSchedule(schedule: Schedule){
    this.scheduleService.create(schedule, this.truck.id).subscribe(
      data => {
        // this.loadSchedule();
        this.newSchedule = new Schedule();
      },
      err => console.log("Observable got an error " + err)
      );

  }

}
