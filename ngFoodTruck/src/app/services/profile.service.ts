import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private url = environment.baseUrl + 'api/profile';

  constructor(private http: HttpClient) { }

  update(updateUser: User, id: number) {
    return this.http.put<User>(this.url + "/" + updateUser.id, updateUser)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ProfileService: error updating Profile');

      })
    );
  }
}
