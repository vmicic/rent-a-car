import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PickupService {

  constructor(
    private http: HttpClient
  ) { }

  getAll() : Observable<HttpResponse<any>> {
    return this.http.get("server/advertisement-service/pickups", {observe: 'response'});
  }
}
