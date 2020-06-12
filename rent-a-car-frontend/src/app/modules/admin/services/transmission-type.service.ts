import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransmissionTypeService {

  constructor(
    private http: HttpClient
  ) { }

  getAll() : Observable<HttpResponse<any>> {
    return this.http.get("server/administrator-service/transmission-type", {observe: 'response'});
  }

  createTransmissionType(transmissionTypeName) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/administrator-service/transmission-type", transmissionTypeName, {observe: 'response'});
  }

  deleteTransmissionType(id: number) : Observable<HttpResponse<any>> {
    return this.http.delete("server/administrator-service/transmission-type/" + id, {observe: 'response'});
  }

  editTransmissionType(id: number, transmissionTypeName) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/administrator-service/transmission-type/" + id, transmissionTypeName, {observe: 'response'});
  }
}
