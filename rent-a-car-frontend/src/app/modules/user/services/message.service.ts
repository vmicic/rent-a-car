import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(
    private http: HttpClient
  ) { }

  sendMessage(message) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/message-service/messages", message, {observe: 'response'});
  }

  getAll() : Observable<HttpResponse<any>> {
    return this.http.get("server/message-service/messages", {observe: 'response'});
  }
}
