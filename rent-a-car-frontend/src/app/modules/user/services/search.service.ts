import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(
    private http: HttpClient
  ) { }

  getSearchResult(paramSearch) : Observable<any> {
    return this.http.get("server/search-service/search", {params: paramSearch, observe: 'response'});
  }
}
