import { Component, OnInit } from '@angular/core';
import {ServerGroupService} from "../../../services/server-group.service";
import {ServerGroup} from "../../../model/ServerGroup";
import {GlobalService} from "../../../global/global.service";

@Component({
  selector: 'app-settings-servers',
  templateUrl: './settings-servers.component.html',
  styleUrls: ['./settings-servers.component.css']
})
export class SettingsServersComponent implements OnInit {

  serverGroupName: string = "";
  submitted = false;
  serverGroups: ServerGroup[] = [];

  constructor(private serverGroupService: ServerGroupService, private globalService: GlobalService) { }

  ngOnInit(): void {
    this.serverGroupService.getAll()
      .subscribe(
        data => {
          this.serverGroups = data;
        },
        error => {
        });
  }

  addNewServerGroup(){
    this.submitted = true;
    if(!this.serverGroupName){
      this.globalService.openSnackBar("Enter the name of the server group!", 2);
      this.submitted = false;
      return;
    }
    this.serverGroupService.addNew(new ServerGroup(0, this.serverGroupName, [])).subscribe(
      result => {
        this.submitted = false;
        this.serverGroups.push(result);
        this.globalService.openSnackBar("New server group added!", 2);
      },
      error => {
        this.submitted = false;
        this.globalService.openSnackBar("Error! " + error["error"]["error"], 10);
      }
    );
  }

  deleteServerGroup(id: number){
    this.serverGroups = this.serverGroups.filter(s => s.id != id);

    this.serverGroupService.delete(id).subscribe(
      result => {
        this.globalService.openSnackBar("Deleted!", 2);
      },
      error => {
        this.globalService.openSnackBar("Error! " + error["error"]["error"], 10);
      }
    );
  }




}
