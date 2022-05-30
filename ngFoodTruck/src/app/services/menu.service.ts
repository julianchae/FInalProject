import { HttpClient } from '@angular/common/http';
import { Injectable} from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { Menu } from '../models/menu';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private url = environment.baseUrl + "api/trucks/menuItem";

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


    index(tid: number) {
      return this.http.get<Menu[]>(this.url + '/' + tid)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
    }
    create(menuItem: Menu, tid: number) {
      console.log(menuItem);
      return this.http.post<Menu>(this.url + '/' + tid + '/', menuItem, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
    }


    update(updateMenuItem: Menu, tid: number, mid: number) {

         return this.http.put<Menu>((this.url + '/' + tid + '/' + mid), updateMenuItem, this.getHttpOptions()).pipe(
           catchError((err: any) => {
             console.log(err);
             return throwError('KABOOM');
           })
         );
       }
       destroy(id: number) {

         return this.http.delete<boolean>(this.url + '/' + id, this.getHttpOptions()).pipe(
           catchError((err: any) => {
             console.log(err);
             return throwError('KABOOM');
           })
         );
       }
}
