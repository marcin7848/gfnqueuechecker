import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import {NgcCookieConsentConfig, NgcCookieConsentModule} from "ngx-cookieconsent";
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import { LastSearchedComponent } from './components/last-searched/last-searched.component';
import { AdsComponent } from './components/ads/ads.component';
import {HttpClientModule} from "@angular/common/http";
import {MatListModule} from "@angular/material/list";
import {MatLineModule} from "@angular/material/core";
import { DateAgoPipe } from './pipes/date-ago.pipe';
import {MatCardModule} from "@angular/material/card";

const cookieConfig:NgcCookieConsentConfig = {
  cookie: {
    domain: 'localhost' // or 'your.domain.com' // it is mandatory to set a domain, for cookies to work properly (see https://goo.gl/S2Hy2A)
  },
  palette: {
    popup: {
      background: '#000'
    },
    button: {
      background: '#f1d600'
    }
  },
  theme: 'edgeless',
  type: 'opt-out'
};

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LastSearchedComponent,
    AdsComponent,
    DateAgoPipe
  ],
    imports: [
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        NgcCookieConsentModule.forRoot(cookieConfig),
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        MatListModule,
        MatLineModule,
        MatCardModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
