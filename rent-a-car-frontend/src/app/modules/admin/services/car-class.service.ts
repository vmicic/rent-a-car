import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarClassService {

  constructor(
    private http: HttpClient
  ) { }

  getAll() : Observable<HttpResponse<any>> {
    return this.http.get("server/administrator-service/class", {observe: 'response'});
  }

  createCarClass(className) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/administrator-service/class", className, {observe: 'response'});
  }

  deleteCarClass(id: number) : Observable<HttpResponse<any>> {
    return this.http.delete("server/administrator-service/class/" + id, {observe: 'response'});
  }

  editClass(id: number, className) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/administrator-service/class/" + id, className, {observe: 'response'});
  }
}
