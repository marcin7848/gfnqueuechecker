import { Injectable } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  constructor(private _snackBar: MatSnackBar) { }

  openSnackBar(message: string, seconds: number) {
    this._snackBar.open(message, 'OK', {
      horizontalPosition: "center",
      verticalPosition: "top",
      duration: seconds * 1000,
      panelClass: ['snackbar']
    });
  }
}
