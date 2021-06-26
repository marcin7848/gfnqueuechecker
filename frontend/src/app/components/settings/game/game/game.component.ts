import { Component, OnInit } from '@angular/core';
import {GameService} from "../../../../services/game.service";
import {ServerGroup} from "../../../../model/ServerGroup";
import {GlobalService} from "../../../../global/global.service";
import {Game} from "../../../../model/Game";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  submitted = false;
  appId: number = 0;
  gameName: string = "";
  coverImg: string = "";
  games: Game[] = [];

  appIdControl = new FormControl('', [
    Validators.pattern('^[0-9]*$'),
  ]);

  constructor(private gameService: GameService, private globalService: GlobalService) { }

  ngOnInit(): void {
  }

  addGame(){
    this.submitted = true;
    if(this.appId == 0 || !this.gameName || !this.coverImg){
      this.globalService.openSnackBar("Fill in all fields!", 2);
      this.submitted = false;
      return;
    }
    this.gameService.addNew(new Game(0, this.appId, this.gameName, this.coverImg, [], []))
      .subscribe(result => {
        this.submitted = false;
        this.games.push(result);
        this.globalService.openSnackBar("New game added!", 2);
      },
      error => {
        this.submitted = false;
        this.globalService.openSnackBar("Error! " + error["error"]["error"], 10);
      }
    );
  }

}
