import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Schedule } from '../models/schedule';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  private url = environment.baseUrl + 'api/schedule/truck';

  constructor(private http: HttpClient) { }

  show(id: number){
    return this.http.get<Schedule[]>(this.url + '/' + id)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ScheduleService: error retrieving Schedule of a Truck');

      })
    )
  }
}
