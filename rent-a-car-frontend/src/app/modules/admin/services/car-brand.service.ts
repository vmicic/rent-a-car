import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarBrandService {

  constructor(
    private http: HttpClient
    ) { }

  getAll() : Observable<HttpResponse<any>> {
    return this.http.get("server/administrator-service/brand", {observe: 'response'});
  }

  createCarBrand(brandName) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/administrator-service/brand", brandName, {observe: 'response'});
  }

  deleteCarBrand(id: number) : Observable<HttpResponse<any>> {
    return this.http.delete("server/administrator-service/brand/" + id, {observe: 'response'});
  }

  editBrand(id: number, brandName) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/administrator-service/brand/" + id, brandName, {observe: 'response'});
  }
}
