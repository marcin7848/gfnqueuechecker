import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Game} from "../model/Game";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  host: string = "http://localhost:8080";
  baseHttp: string = this.host + "/game/";

  constructor(private http: HttpClient) { }

  addNew(game: Game): Observable<Game> {
    return this.http.post<Game>(this.baseHttp + 'add', game);
  }
}
