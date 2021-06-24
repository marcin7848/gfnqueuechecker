import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LastSearched} from "../model/LastSearched";

@Injectable({
  providedIn: 'root'
})
export class LastSearchedService {

  host: string = "http://localhost:8080";
  baseHttp: string = this.host + "/lastSearched/";

  constructor(private http: HttpClient) { }

  getLastSearched(): Observable<LastSearched[]> {
    return this.http.get<LastSearched[]>(this.baseHttp + 'get');
  }
}
