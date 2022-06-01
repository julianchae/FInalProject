import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Schedule } from '../models/schedule';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  private url = environment.baseUrl + 'api/schedule';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  show(id: number){
    return this.http.get<Schedule[]>(this.url + '/truck/' + id)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ScheduleService: error retrieving Schedule of a Truck');

      })
    )
  }
  show2(id: number){
    return this.http.get<Schedule[]>(this.url + '/user/truck' )
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ScheduleService: error retrieving Schedule of a Truck');

      })
    )
  }
  // schedule/truck/{tid}/location/{lid}
  create(schedule: Schedule, tid: number, lid: number) {
    console.log(schedule);
    return this.http.post<Schedule>(this.url + '/' + tid + '/location/' + lid, schedule, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }

  update(updateSchedule: Schedule, tid: number, lid: number) {

    return this.http.put<Schedule>(this.url + '/' + tid + '/location/' + lid, updateSchedule, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }
}
