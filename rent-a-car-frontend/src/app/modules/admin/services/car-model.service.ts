import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarModelService {

  constructor(
    private http: HttpClient
  ) { }

  getAll() : Observable<HttpResponse<any>> {
    return this.http.get("server/administrator-service/model", {observe: 'response'});
  }

  createCarModel(model) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/administrator-service/model", model, {observe: 'response'});
  }

  deleteCarModel(id: number) : Observable<HttpResponse<any>> {
    return this.http.delete("server/administrator-service/model/" + id, {observe: 'response'});
  }

  editCarModel(id: number, model) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/administrator-service/model/" + id, model, {observe: 'response'});
  }
}
