import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(
    private http: HttpClient
  ) { }

  getAll() : Observable<HttpResponse<any>> {
    return this.http.get("server/user-service/users", {observe: 'response'});
  }
  
  activate(id) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/user-service/users/activate/" + id, {observe: 'response'});
  }

  deactivate(id) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/user-service/users/deactivate/" + id, {observe: 'response'});
  }

  delete(id) : Observable<HttpResponse<any>> {
    return this.http.delete<any>("server/user-service/users/" + id, {observe: 'response'});
  }
}
