import { Component, OnInit } from '@angular/core';
import {GameService} from "../../services/game.service";
import {Game} from "../../model/Game";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchGameName: string = "";
  appId: number;
  games: Game[] = [];

  constructor(private gameService: GameService) { }

  ngOnInit(): void {

  }

  valueChanged(event: any){
    let gameName = event.target.value;
    if(gameName.trim().length > 3){
      this.gameService.getGamesByGameNameContaining(gameName)
        .subscribe(result => {
            this.games = result;
          },
          error => {
          }
        );
    }else{
      this.games = [];
    }
  }

  selectedGameName(gameName: string){
    let selectedGame = this.games.filter(g => g.gameName == gameName).pop();
    console.log(selectedGame);
  }
}
