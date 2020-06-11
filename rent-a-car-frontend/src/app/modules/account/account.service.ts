import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/shared/domain/user';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(
    private http: HttpClient
  ) { }

  login(user: User) : Observable<HttpResponse<any>> {
    return this.http.post<User>("server/user-service/auth/login", user, {observe: 'response'});
  }

}
