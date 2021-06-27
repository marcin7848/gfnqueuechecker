import {Component, OnInit, ViewChild} from '@angular/core';
import {GameService} from "../../../services/game.service";
import {ServerGroup} from "../../../model/ServerGroup";
import {GlobalService} from "../../../global/global.service";
import {Game} from "../../../model/Game";
import {FormControl, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

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


  displayedColumns: string[] = ['appId', 'gameName', 'coverImg', 'action'];
  dataSource: MatTableDataSource<Game>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private gameService: GameService, private globalService: GlobalService) { }

  ngOnInit(): void {
    this.gameService.getAll()
      .subscribe(
        data => {
          this.games = data;
          this.dataSource = new MatTableDataSource(this.games);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error => {
        });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
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
        this.dataSource = new MatTableDataSource(this.games);
        this.globalService.openSnackBar("New game added!", 2);
      },
      error => {
        this.submitted = false;
        this.globalService.openSnackBar("Error! " + error["error"]["error"], 10);
      }
    );
  }

  deleteGame(id: number){
    this.games = this.games.filter(s => s.id != id);
    this.dataSource = new MatTableDataSource(this.games);

    /*
    this.gameService.delete(id).subscribe(
      result => {
        this.globalService.openSnackBar("Deleted!", 2);
      },
      error => {
        this.globalService.openSnackBar("Error! " + error["error"]["error"], 10);
      }
    );*/
  }

}
