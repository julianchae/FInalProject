import { LocationService } from './../../services/location.service';
import { Location } from './../../models/location';
import { Component, Input, OnInit } from '@angular/core';
import { FoodTruck } from 'src/app/models/food-truck';
import { Schedule } from 'src/app/models/schedule';
import { AuthService } from 'src/app/services/auth.service';
import { ScheduleService } from 'src/app/services/schedule.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css'],
})
export class ScheduleComponent implements OnInit {
  @Input() truck: FoodTruck = new FoodTruck();
  schedule: Schedule[] = [];
  location: Location = new Location();
  newLocation: Location = new Location();
  selected: Schedule | null = null;

  newSchedule: Schedule = new Schedule();
  constructor(
    private scheduleService: ScheduleService,
    private authService: AuthService,
    private locationService: LocationService
  ) {}

  ngOnInit(): void {
    this.show(this.truck.id);
  }

  show(id: number) {
    this.scheduleService.show(id).subscribe(
      (success) => {
        this.schedule = success;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  createSchedule(schedule: Schedule, location: Location) {
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
        schedule.location = this.newLocation;
        this.createScheduleHttp(schedule, location);
        console.log(data);
      },
      (err) => {
        this.locationService.createLocation(location).subscribe(
          (data) => {
            this.newLocation = data;
            schedule.location = this.newLocation;
            this.createScheduleHttp(schedule, location);
            console.log(data);
          },
          (err) => {
            console.log(err);
          }
        );
      }
    );
  }

  createScheduleHttp(schedule: Schedule, location: Location) {
    this.scheduleService
      .create(schedule, this.truck.id, this.newLocation.id)
      .subscribe(
        (data) => {
          this.newSchedule = data;
        },
        (err) => console.log('Observable got an error ' + err)
      );
  }
}
