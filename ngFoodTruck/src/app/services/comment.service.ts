import { Comment } from './../models/comment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private url = environment.baseUrl + "api/comments";

  private createCommentUrl = environment.baseUrl +"api/users/comment"

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
  return this.http.get<Comment[]>(this.url+ '/index')
  .pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('KABOOM');
    })
  );
}
getCommentsOnTruck(id: number) {
  return this.http.get<Comment[]>(this.url + '/' + id)
  .pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('KABOOM');
    })
  );
}

createCommentOnTruck(id:number, comment: Comment){
  return this.http.post<Comment>(this.createCommentUrl+'/'+ id, comment, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('KABOOM');
    })
  );



}








}
