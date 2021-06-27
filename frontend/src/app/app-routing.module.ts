import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SettingsComponent} from "./components/settings/settings.component";
import {SearchComponent} from "./components/search/search.component";
import {LoginComponent} from "./components/login/login.component";

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
