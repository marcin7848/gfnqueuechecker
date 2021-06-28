import { Component, OnInit } from '@angular/core';
import {GameService} from "../../services/game.service";
import {Game} from "../../model/Game";
import {ServerGroup} from "../../model/ServerGroup";
import {ServerGroupService} from "../../services/server-group.service";
import {CheckQueueService} from "../../services/check-queue.service";
import {GlobalService} from "../../global/global.service";
import {CheckQueue} from "../../model/CheckQueue";
import {Router} from "@angular/router";

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
              private checkQueueService: CheckQueueService, private globalService: GlobalService,
              private router: Router) { }

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
        this.searchKey = data.searchKey;
        setTimeout(() => {
          this.processCheckQueue(0);
        }, 500);

      },
      error => {
        this.globalService.openSnackBar("Error! " + error["error"]["error"], 5);
      });
  }

  processCheckQueue(i: number){
    console.log(i);
    if(i > 100){
      this.globalService.openSnackBar("Can't get queues for all servers! Try again later!", 5);
      setTimeout(() => {
        this.router.navigate(['/']);
      }, 5000)
      return;
    }

    this.checkQueueService.getCheckQueuesBySearchKey(this.searchKey).subscribe(
      data => {
        this.checkQueues = data;

        if(this.checkQueues.filter(cq => cq.process == 0 || cq.process == 1).length > 0){
          setTimeout(() => {
            this.processCheckQueue(++i);
          }, 3000);
        }
      },
      error => {
        this.globalService.openSnackBar("Error! " + error["error"]["error"], 5);
      });
  }

  checkingDone(serverId: number): boolean{
    return this.checkQueues.filter(cq => (cq.process == 2 || cq.process == 3) && cq.server.id == serverId).length > 0;
  }

  getProcessPositionAndETA(serverId: number){
    let checkQueue = this.checkQueues.filter(cq => cq.server.id == serverId).pop();
    if(!checkQueue)
      return {process: 3, positionIsQueue: -1, ETA: -1};

    return {process: checkQueue.process, positionIsQueue: checkQueue.positionInQueue, eta: checkQueue.eta};
  }

  covertMillisToMinutesAndSeconds(millis: number): string{
    let minutes = Math.floor(millis / 60000);
    let seconds = +((millis % 60000) / 1000).toFixed(0);

    return minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
  }

  convertQueueString(positionInQueue: number | undefined, eta: number | undefined): string{
    if(!positionInQueue || !eta){
      return "";
    }

    if(positionInQueue <= 5){
      return "Instant";
    }

    return "Queue: " + positionInQueue + ", ETA: " + this.covertMillisToMinutesAndSeconds(eta) + " minutes";

  }

}
