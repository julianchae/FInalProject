import { User } from './../../models/user';
import { LocationService } from './../../services/location.service';
import { Location } from './../../models/location';
import { Component, Input, OnInit } from '@angular/core';
import { FoodTruck } from 'src/app/models/food-truck';
import { Schedule } from 'src/app/models/schedule';
import { AuthService } from 'src/app/services/auth.service';
import { ScheduleService } from 'src/app/services/schedule.service';
import { DomSanitizer } from '@angular/platform-browser';
import { DatePipe } from '@angular/common';

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
  user: User | null = null;
  addSchedule: boolean = false;
  editSchedule: Schedule | null = null;

  newSchedule: Schedule = new Schedule();
  constructor(
    private scheduleService: ScheduleService,
    private authService: AuthService,
    private locationService: LocationService,
    private sanitizer: DomSanitizer,

  ) {}

  ngOnInit(): void {
    this.show(this.truck.id);
    this.authService.getLoggedInUser().subscribe({
      next:(user) => {
        this.user = Object.assign({},user);


      },
      error:(fail) => {
        console.error(fail);
        // return null;
      }
    });

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
          this.show(this.truck.id);
          this.newSchedule = data;
        },
        (err) => console.log('Observable got an error ' + err)
      );
  }

  mapsUrl(location: Location) {
    let url = "https://www.google.com/maps/embed/v1/place?key=AIzaSyCyU6ofgUclS8SXStH03I61fVaLSf7Cuv0&q="
    url+= location.street;
    url+= location.city;
    url+= location.state
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  userOwnsTruck(): boolean {
    let isOwner: boolean = false;
    if (this.user?.id === this.truck.user?.id) {
      isOwner = true;
    }
    return isOwner;
  }

  updateSchedule(schedule: Schedule, location: Location){
    if (
      !schedule.id ||
      !schedule.location ||
      !location.street ||
      !location.state ||
      !location.city ||
      !location.zip
      ){
      return
    }
    this.locationService.findByStreet(location.street).subscribe(
      (data) => {
        this.newLocation = data;
        schedule.location = this.newLocation;
        this.updateScheduleHttp(schedule, location);
        console.log(data);
      },
      (err) => {
        this.locationService.update(location, location.id).subscribe(
          (data) => {
            this.newLocation = data;
            schedule.location = this.newLocation;
            this.updateScheduleHttp(schedule, location);
            console.log(data);
          },
          (err) => {
            console.log(err);
          }
        );
      }
    );
    this.scheduleService.update(schedule, schedule.id, schedule.location.id).subscribe(
      data => {
        this.selected = null;
        this.editSchedule = null;
      }
    );
  }
  setEditSchedule(schedule: Schedule) {
    this.editSchedule = Object.assign({}, schedule);
    this.location = Object.assign({}, schedule.location);
    console.log(this.editSchedule);
  }

  updateScheduleHttp(schedule: Schedule, location: Location) {
    this.scheduleService
      .update(schedule, this.truck.id, this.newLocation.id)
      .subscribe(
        (data) => {
          this.newSchedule = data;
          this.show(this.truck.id);
        },
        (err) => console.log('Observable got an error ' + err)
      );
  }

}
