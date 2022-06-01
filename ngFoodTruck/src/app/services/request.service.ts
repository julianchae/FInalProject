import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Request } from '../models/request';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class RequestService {

  private createUrl = environment.baseUrl + "api/requests";
  private url = environment.baseUrl+ "api/users/requests";

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
userCanCreateRequest(tid: number, lid: number, request: Request){
  return this.http.post<Request>(this.url+'/'+ tid+'/'+ lid, request,
   this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('KABOOM');
    })
  );
  }

  getRequestsOnTruck(id: number) {
    return this.http.get<Request[]>(this.createUrl + '/' + id, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }

  updateRequest(req: Request, id: number) {
    return this.http.put<Request>(this.createUrl + '/' + id, req, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }

  getUserRequests() {
    return this.http.get<Request[]>(this.createUrl, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }






}
