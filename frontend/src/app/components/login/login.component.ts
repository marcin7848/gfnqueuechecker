import { Component, OnInit } from '@angular/core';
import {AccountService} from "../../services/account.service";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  submitted = false;
  username: String = "";
  password: String = "";

  usernameControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-zA-Z0-9_]+$')
  ]);

  passwordControl = new FormControl('', [
    Validators.required
  ]);

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
  }

  login(){

  }

}
