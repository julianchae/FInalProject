import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { Menu } from '../models/menu';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private url = environment.baseUrl + "api/trucks";

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


    index() {
      return this.http.get<Menu[]>(this.url)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
    }
    create(menuItem: Menu) {
      return this.http.post<Menu>(this.url, menuItem, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
    }


    update(updateMenuItem: Menu) {
      //TODO: update routes!!!
         return this.http.put<Menu>((this.url + '/menuItem/' + updateMenuItem.id), updateMenuItem, this.getHttpOptions()).pipe(
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
