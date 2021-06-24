import {LastSearched} from "./LastSearched";
import {CheckQueue} from "./CheckQueue";

export class Game {
  id: number;
  appId: number;
  gameName: string;
  coverImg: string;
  lastSearched: LastSearched[];
  checkQueue: CheckQueue[];

  constructor(id: number, appId: number, gameName: string, coverImg: string, lastSearched: LastSearched[],
              checkQueue: CheckQueue[]) {
    this.id = id;
    this.appId = appId;
    this.gameName = gameName;
    this.coverImg = coverImg;
    this.lastSearched = lastSearched;
    this.checkQueue = checkQueue;
  }

}
