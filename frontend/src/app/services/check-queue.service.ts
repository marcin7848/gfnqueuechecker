import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SearchKey} from "../model/SearchKey";

@Injectable({
  providedIn: 'root'
})
export class CheckQueueService {

  host: string = "http://localhost:8080";
  baseHttp: string = this.host + "/checkQueue/";

  constructor(private http: HttpClient) { }

  generateCheckQueue(gameId: number): Observable<SearchKey> {
    return this.http.post<SearchKey>(this.baseHttp + 'generate/game/' + gameId, null);
  }
}
