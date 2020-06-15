import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpRequest } from '@angular/common/http';
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

  addCar(car) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/advertisement-service/cars", car, {observe: 'response'});
  }

  sendImage(formData) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/advertisement-service/cars/uploadImage", formData, {observe: 'response'});
  }

  getCar(id) : Observable<any> {
    return this.http.get("server/advertisement-service/cars/" + id);
  }

  getImages(id) : Observable<any> {
    return this.http.get("server/advertisement-service/cars/images/" + id);
  }
}
