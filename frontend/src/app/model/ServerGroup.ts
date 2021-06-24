import {Server} from "./Server";

export class ServerGroup {
  id: number;
  serverGroupName: string;
  servers: Server[];

  constructor(id: number, serverGroupName: string, servers: Server[]) {
    this.id = id;
    this.serverGroupName = serverGroupName;
    this.servers = servers;
  }

}
