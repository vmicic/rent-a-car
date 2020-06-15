import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  constructor(
    private http: HttpClient
  ) { }

  getAllCarsFromUser() : Observable<HttpResponse<any>> {
    return this.http.get("server/advertisement-service/cars/user", {observe: 'response'});
  }
}
