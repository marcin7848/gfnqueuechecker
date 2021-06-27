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
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatListModule} from "@angular/material/list";
import {MatLineModule, MatOptionModule} from "@angular/material/core";
import { DateAgoPipe } from './pipes/date-ago.pipe';
import {MatCardModule} from "@angular/material/card";
import { SettingsComponent } from './components/settings/settings.component';
import { SearchComponent } from './components/search/search.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { SettingsServersComponent } from './components/settings/settings-servers/settings-servers.component';
import {MatSelectModule} from "@angular/material/select";
import {GameComponent} from "./components/settings/game/game.component";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import { LoginComponent } from './components/login/login.component';
import {CookieService} from "ngx-cookie-service";
import { LogoutComponent } from './components/logout/logout.component';
import {Interceptor} from "./app.interceptor";

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
    DateAgoPipe,
    SettingsComponent,
    SearchComponent,
    SettingsServersComponent,
    GameComponent,
    LoginComponent,
    LogoutComponent
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
    MatCardModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatSnackBarModule,
    MatSelectModule,
    MatOptionModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule
  ],
  providers: [CookieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
