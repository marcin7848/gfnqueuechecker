import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {
  NgcCookieConsentService,
  NgcInitializeEvent,
  NgcNoCookieLawEvent,
  NgcStatusChangeEvent
} from "ngx-cookieconsent";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'appComponent';

  private popupOpenSubscription: Subscription | undefined;
  private popupCloseSubscription: Subscription | undefined;
  private initializeSubscription: Subscription | undefined;
  private statusChangeSubscription: Subscription | undefined;
  private revokeChoiceSubscription: Subscription | undefined;
  private noCookieLawSubscription: Subscription | undefined;

  constructor(private ccService: NgcCookieConsentService){}

  ngOnInit() {
    this.popupOpenSubscription = this.ccService.popupOpen$.subscribe(() => {});
    this.popupCloseSubscription = this.ccService.popupClose$.subscribe(() => {});
    this.initializeSubscription = this.ccService.initialize$.subscribe((event: NgcInitializeEvent) => {});
    this.statusChangeSubscription = this.ccService.statusChange$.subscribe(
      (event: NgcStatusChangeEvent) => {
        if(event.status == "deny"){
          window.location.href = "https://google.com/";
        }
      });
    this.revokeChoiceSubscription = this.ccService.revokeChoice$.subscribe(() => {});
    this.noCookieLawSubscription = this.ccService.noCookieLaw$.subscribe((event: NgcNoCookieLawEvent) => {});
  }
}
