import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {JwtAuth} from "../model/JwtAuth";
import {Account} from "../model/Account";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  host: string = "http://localhost:8080";
  baseHttp: string = this.host + "/account/";

  constructor(private http: HttpClient) { }

  login(account: Account): Observable<JwtAuth> {
    return this.http.post<JwtAuth>(this.baseHttp + 'login', account);
  }
}
