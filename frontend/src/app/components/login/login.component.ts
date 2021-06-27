import { Component, OnInit } from '@angular/core';
import {AccountService} from "../../services/account.service";
import {FormControl, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {GlobalService} from "../../global/global.service";
import {Account} from "../../model/Account";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  submitted = false;
  username: string = "";
  password: string = "";

  usernameControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-zA-Z0-9_]+$')
  ]);

  passwordControl = new FormControl('', [
    Validators.required
  ]);

  constructor(private accountService: AccountService,
              private cookieService: CookieService,
              private router: Router,
              private globalService: GlobalService) { }

  ngOnInit(): void {
    if (this.globalService.isLogged()) {
      this.router.navigate(['/']);
    }
  }

  login(){
    this.submitted = true;
    if(!this.username || !this.password){
      this.globalService.openSnackBar("Fill in all fields!", 2);
      this.submitted = false;
      return;
    }

    this.accountService.login(new Account(0, this.username, this.password, 0))
      .subscribe(result => {
          this.submitted = false;
          this.cookieService.set("Authorization", result['Authorization'], 365, '/');
          this.router.navigate(['/']);
        },
        error => {
          this.submitted = false;
          this.globalService.openSnackBar("Error! " + error["error"]["error"], 10);
        }
      );
  }

}
