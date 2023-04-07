import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AppComponent} from "./app.component";
import {Routes} from "@angular/router";
import {Error404Component} from "./home/error404/error404.component";

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})


export class AppRoutingModule { }
export const routingComponents = [AppComponent,Error404Component]