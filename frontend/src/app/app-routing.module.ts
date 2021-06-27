import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SettingsComponent} from "./components/settings/settings.component";
import {SearchComponent} from "./components/search/search.component";
import {LoginComponent} from "./components/login/login.component";
import {LogoutComponent} from "./components/logout/logout.component";

const routes: Routes = [
  {
    path : '',
    component: SearchComponent
  },
  {
    path : 'settings',
    component: SettingsComponent
  },
  {
    path : 'login',
    component: LoginComponent
  },
  {
    path : 'logout',
    component: LogoutComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
