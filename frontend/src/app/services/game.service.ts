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

  getAll(): Observable<Game[]>{
    return this.http.get<Game[]>(this.baseHttp + 'getAll');
  }

  addNew(game: Game): Observable<Game> {
    return this.http.post<Game>(this.baseHttp + 'add', game);
  }

  delete(id: number) {
    return this.http.delete(this.baseHttp + id + '/delete');
  }
}
