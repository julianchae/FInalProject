import { Location } from './../models/location';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LocationService {
  private url = environment.baseUrl + "api/locations";

  constructor(private http: HttpClient,
    private authServ: AuthService) { }

    getHttpOptions() {
      let options = {
        headers: {
          Authorization: 'Basic ' + this.authServ.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
        },
      };
      return options;
    }

    findByStreet(street: string) {
      return this.http.get<Location>(this.url + '/search/' + street, this.getHttpOptions() )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
    }

    createLocation(location: Location) {
      return this.http.post<Location>(this.url, location, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
    }

    update(upLocation: Location, lid: number) {
      return this.http.put<Location>((this.url + '/' + lid), upLocation, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
    }
}
