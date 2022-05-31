import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { FoodTruck } from '../models/food-truck';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private url = environment.baseUrl + 'api/trucks/';
  constructor(private http: HttpClient) { }


}
