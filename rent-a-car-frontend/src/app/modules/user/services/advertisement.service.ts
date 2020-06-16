import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdvertisementService {

  constructor(
    private http: HttpClient
  ) { }

  createAdvertisement(ad) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/advertisement-service/advertisement", ad, {observe: 'response'});
  }
}
