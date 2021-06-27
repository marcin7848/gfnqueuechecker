import { Component, OnInit } from '@angular/core';
import {ConfigService} from "../../services/config.service";
import {Config} from "../../model/Config";
import {GlobalService} from "../../global/global.service";
import {Router} from "@angular/router";

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

  constructor(private configService: ConfigService, private globalService: GlobalService, private router: Router) { }

  ngOnInit(): void {
    if (!this.globalService.isLogged()) {
      this.router.navigate(['/']);
    }

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
        this.globalService.openSnackBar("Saved!", 2);
      },
      error => {
        this.submitted = false;
        this.globalService.openSnackBar("Error! " + error["error"]["error"], 10);
      }
    );

  }

}
