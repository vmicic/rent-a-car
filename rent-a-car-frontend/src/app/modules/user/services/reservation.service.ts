import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(
    private http: HttpClient
  ) { }

  createReservationApproved(reservation) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/advertisement-service/reservation/approved", reservation, {observe: 'response'});
  }

  createReservation(reservation) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/advertisement-service/reservation", reservation, {observe: 'response'});
  }

  getReservationsForApproval() : Observable<HttpResponse<any>> {
    return this.http.get("server/advertisement-service/reservation/approval", {observe: 'response'});
  }

  getReservationsRequested() : Observable<HttpResponse<any>> {
    return this.http.get("server/advertisement-service/reservation", {observe: 'response'});
  }
  approveReservation(id) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/advertisement-service/reservation/approve/" + id, {observe: 'response'});
  }

  reject(id) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/advertisement-service/reservation/reject/" + id, {observe: 'response'});
  }
}
