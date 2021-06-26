import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ServerGroup} from "../model/ServerGroup";
import {Server} from "../model/Server";

@Injectable({
  providedIn: 'root'
})
export class ServerGroupService {

  host: string = "http://localhost:8080";
  baseHttp: string = this.host + "/serverGroup/";

  constructor(private http: HttpClient) { }

  getAll(): Observable<ServerGroup[]> {
    return this.http.get<ServerGroup[]>(this.baseHttp + 'getAll');
  }

  addNew(serverGroup: ServerGroup): Observable<ServerGroup> {
    return this.http.post<ServerGroup>(this.baseHttp + 'add', serverGroup);
  }

  delete(id: number) {
    return this.http.delete(this.baseHttp + id + '/delete');
  }

  addNewServer(serverGroupId: number, server: Server): Observable<Server> {
    return this.http.post<Server>(this.baseHttp + serverGroupId + '/server/add', server);
  }

  deleteServer(serverGroupId: number, id: number) {
    return this.http.delete(this.baseHttp + serverGroupId + '/server/' + id + '/delete');
  }

}
