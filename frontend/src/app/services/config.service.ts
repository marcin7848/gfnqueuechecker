import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Config} from "../model/Config";

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  host: string = "http://localhost:8080";
  baseHttp: string = this.host + "/config/";

  constructor(private http: HttpClient) { }

  getConfigsForSettings(): Observable<Config[]> {
    return this.http.get<Config[]>(this.baseHttp + 'getForSettings');
  }

  editSettings(configs: Config[]): Observable<Config> {
    return this.http.post<Config>(this.baseHttp + 'editSettings', configs);
  }

}
