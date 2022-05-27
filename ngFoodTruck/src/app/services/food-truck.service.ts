import { AuthService } from 'src/app/services/auth.service';
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

  constructor(private http: HttpClient,
      private authServ: AuthService
    ) { }

    getHttpOptions() {
      let options = {
        headers: {
          Authorization: 'Basic ' + this.authServ.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
        },
      };
      return options;
    }

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

  create(foodTruck: FoodTruck) {
    return this.http.post<FoodTruck>(this.url, foodTruck, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }
  update(updateFoodTruck: FoodTruck) {
 //TODO: update routes!!!
    return this.http.put<FoodTruck>((this.url + '/foodtruck/' + updateFoodTruck.id), updateFoodTruck, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }
  destroy(id: number) {
//TODO: update routes!!!
    return this.http.delete<boolean>(this.url + '/foodtruck/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }

}
