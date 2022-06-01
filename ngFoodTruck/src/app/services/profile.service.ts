import { AuthService } from 'src/app/services/auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class ProfileService {


  private url = environment.baseUrl + 'api/users';
  private urlTruck = environment.baseUrl + 'api/trucks';
  user: User = new User();


  constructor(private http: HttpClient, private authServ: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authServ.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  updateUser(updateUser: User, id: number) {
    return this.http.put<User>(this.url + "/" + updateUser.id, updateUser, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ProfileService: error updating Profile');

      })
    );
  }
  // create(foodTruck: FoodTruck, tid: number) {
  //   // console.log(menuItem);
  //   return this.http.post<FoodTruck>(this.urlTruck + '/trucks', this.getHttpOptions()).pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError('KABOOM');
  //     })
  //   );
  // }

  // index(tid: number) {
  //   return this.http.get<FoodTruck[]>(this.urlTruck + '/single/' + tid)
  //   .pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError('KABOOM');
  //     })
  //   );
  // }
  // updateFoodTruck(updateFoodTruck: FoodTruck) {
  //   //TODO: update routes!!!
  //      return this.http.put<FoodTruck>((this.url + '/foodtruck/' + updateFoodTruck.id), updateFoodTruck, this.getHttpOptions()).pipe(
  //        catchError((err: any) => {
  //          console.log(err);
  //          return throwError('KABOOM');
  //        })
  //      );
  //    }

}
