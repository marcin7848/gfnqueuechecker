import {ServerGroup} from "./ServerGroup";
import {CheckQueue} from "./CheckQueue";

export class Server {
  id: number;
  serverHost: string;
  serverName: string;
  serverGroup: ServerGroup;
  checkQueue: CheckQueue[];

  constructor(id: number, serverHost: string, serverName: string, serverGroup: ServerGroup, checkQueue: CheckQueue[]) {
    this.id = id;
    this.serverHost = serverHost;
    this.serverName = serverName;
    this.serverGroup = serverGroup;
    this.checkQueue = checkQueue;
  }

}
