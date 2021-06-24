import {Game} from "./Game";

export class LastSearched {
  id: number;
  searchTime: Date;
  game: Game;

  constructor(id: number, searchTime: Date, game: Game) {
    this.id = id;
    this.searchTime = searchTime;
    this.game = game;
  }

}
