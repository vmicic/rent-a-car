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
}
