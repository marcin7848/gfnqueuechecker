import { Component, OnInit } from '@angular/core';
import {LastSearchedService} from "../../services/last-searched.service";
import {LastSearched} from "../../model/LastSearched";

@Component({
  selector: 'app-last-searched',
  templateUrl: './last-searched.component.html',
  styleUrls: ['./last-searched.component.css']
})
export class LastSearchedComponent implements OnInit {

  lastSearched: LastSearched[] | undefined;

  constructor(private lastSearchedService: LastSearchedService) { }

  ngOnInit(): void {
    this.lastSearchedService.getLastSearched()
      .subscribe(
        data => {
          this.lastSearched = data;
          console.log(this.lastSearched);
        },
        error => {

        });
  }

}
