import { Component, OnInit } from '@angular/core';
import {GameService} from "../../services/game.service";
import {Game} from "../../model/Game";
import {ServerGroup} from "../../model/ServerGroup";
import {ServerGroupService} from "../../services/server-group.service";
import {CheckQueueService} from "../../services/check-queue.service";
import {SearchKey} from "../../model/SearchKey";
import {GlobalService} from "../../global/global.service";
import {CheckQueue} from "../../model/CheckQueue";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchGameName: string = "";
  appId: number;
  games: Game[] = [];
  selectedGame: Game | undefined;
  serverGroups: ServerGroup[] = [];
  searchKey: string = "";
  checkQueues: CheckQueue[] = [];

  constructor(private gameService: GameService, private serverGroupService: ServerGroupService,
              private checkQueueService: CheckQueueService, private globalService: GlobalService) { }

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
    this.selectedGame = this.games.filter(g => g.gameName == gameName).pop();
    if(!this.selectedGame)
      return;

    this.serverGroupService.getAll()
      .subscribe(
        data => {
          this.serverGroups = data;
        },
        error => {
        });

    this.checkQueueService.generateCheckQueue(this.selectedGame.id).subscribe(
      data => {
        this.searchKey = data.SearchKey;
        setTimeout(this.processCheckQueue, 500);

      },
      error => {
        this.globalService.openSnackBar("Error! " + error["error"]["error"], 5);
      });
  }

  processCheckQueue(){

  }
}
