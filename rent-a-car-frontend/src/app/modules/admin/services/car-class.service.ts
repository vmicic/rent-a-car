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
}
