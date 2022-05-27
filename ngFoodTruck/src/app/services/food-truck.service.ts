import { HttpClient } from '@angular/common/http';
import { FoodTruck } from './../models/food-truck';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodTruckService {

  private url = environment.baseUrl + "api/trucks";

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<FoodTruck[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }

  getSingleTruck(id: number) {
    return this.http.get<FoodTruck>(this.url + '/single/' + id)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }

}
