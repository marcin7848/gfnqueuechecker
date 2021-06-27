import { Injectable } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  constructor(private _snackBar: MatSnackBar, private cookieService: CookieService) { }

  openSnackBar(message: string, seconds: number) {
    this._snackBar.open(message, 'OK', {
      horizontalPosition: "center",
      verticalPosition: "top",
      duration: seconds * 1000,
      panelClass: ['snackbar']
    });
  }

  isLogged(): boolean {
    return !!this.cookieService.get("Authorization");
  }
}
