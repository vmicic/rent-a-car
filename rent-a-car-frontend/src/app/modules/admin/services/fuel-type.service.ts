import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FuelTypeService {

  constructor(
    private http: HttpClient
  ) { }

  getAll() : Observable<HttpResponse<any>> {
    return this.http.get("server/administrator-service/fuel-type", {observe: 'response'});
  }

  createFuelType(brandName) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/administrator-service/fuel-type", brandName, {observe: 'response'});
  }

  deleteFuelType(id: number) : Observable<HttpResponse<any>> {
    return this.http.delete("server/administrator-service/fuel-type/" + id, {observe: 'response'});
  }

  editFuelType(id: number, brandName) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/administrator-service/fuel-type/" + id, brandName, {observe: 'response'});
  }
}
