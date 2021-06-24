import {Game} from "./Game";
import {Server} from "./Server";

export class CheckQueue {
  id: number;
  searchKey: string;
  addTime: Date;
  role: number;
  process: number;
  positionInQueue: number;
  eta: number;
  game: Game;
  server: Server;

  constructor(id: number, searchKey: string, addTime: Date, role: number, process: number, positionInQueue: number,
              eta: number, game: Game, server: Server) {
    this.id = id;
    this.searchKey = searchKey;
    this.addTime = addTime;
    this.role = role;
    this.process = process;
    this.positionInQueue = positionInQueue;
    this.eta = eta;
    this.game = game;
    this.server = server;
  }

}
