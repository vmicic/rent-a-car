import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable, Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  constructor(
    private http: HttpClient
  ) { }

  getAllRatingsForApproval() : Observable<HttpResponse<any>> {
    return this.http.get("server/advertisement-service/ratings", {observe: 'response'});
  }

  approveRating(id) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/advertisement-service/ratings/approve/" + id, {observe: 'response'});
  }

  rejectRating(id) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/advertisement-service/ratings/reject/" + id, {observe: 'response'});
  }
}
