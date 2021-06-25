import { Component, OnInit } from '@angular/core';
import {ConfigService} from "../../services/config.service";
import {Config} from "../../model/Config";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  configs: Config[] | undefined;
  authorizationValue: string = "";
  xDeviceIdValue: string = "";
  submitted = false;

  constructor(private configService: ConfigService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.configService.getConfigsForSettings()
      .subscribe(
        data => {
          this.configs = data;
          this.authorizationValue = this.configs.find(x => x.configName == "Authorization")?.configValue || "";
          this.xDeviceIdValue = this.configs.find(x => x.configName == "X-Device-Id")?.configValue || "";

        },
        error => {

        });
  }

  editSettings() {
    this.submitted = true;
    let newConfigs:Config[] = [
      new Config(0, "Authorization", this.authorizationValue),
      new Config(0, "X-Device-Id", this.xDeviceIdValue)
    ]

    this.configService.editSettings(newConfigs).subscribe(
      result => {
        this.submitted = false;
        this.openSnackBar("Saved!", 2);
      },
      error => {
        this.submitted = false;
        this.openSnackBar("Error! " + error["error"]["error"], 10);
      }
    );

  }

  openSnackBar(message: string, seconds: number) {
    this._snackBar.open(message, 'OK', {
      horizontalPosition: "center",
      verticalPosition: "top",
      duration: seconds * 1000,
      panelClass: ['snackbar']
    });
  }

}
