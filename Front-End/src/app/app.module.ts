import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {AlertModule} from "ngx-bootstrap/alert";
import { HeaderComponent } from './home/header/header.component';
import { EmployeeWindowComponent } from './home/employee-window/employee-window.component';
import { EmployeeComponent } from './home/employee-window/employee/employee.component';
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ModalComponent, } from './home/ModalWIndows/modal-addEmployee/modal.component';
import {MdbModalModule, MdbModalRef, MdbModalService} from 'mdb-angular-ui-kit/modal';
import {FormsModule} from "@angular/forms";
import { ModalDeleteComponent } from './home/ModalWIndows/modal-delete-employee/modal-delete-component';
import { ElementRefContainerDirective } from './element-ref-container.directive';
import { ModalEditEmployeeComponent } from './home/ModalWIndows/modal-edit-employee/modal-edit-employee.component';
import {SearchPipe} from "./HelperClass/SearchPipe/searchPipe";
import {ReactiveFormsModule} from "@angular/forms";
import {MdbDropdownModule} from "mdb-angular-ui-kit/dropdown";
import { Error404Component } from './home/error404/error404.component';
import {AppRoutingModule} from "./app-routing.module";
import {RouterModule, Routes} from "@angular/router";
import { HomeComponent } from './home/home.component';

const routes: Routes = [
    {path: 'home', component: HomeComponent},


    { path: '**',
        component: Error404Component },];


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    EmployeeWindowComponent,
    EmployeeComponent,
    ModalComponent,
    ModalDeleteComponent,
    ElementRefContainerDirective,
    ModalEditEmployeeComponent,
    SearchPipe,
    Error404Component,
    HomeComponent,


  ],
    imports: [
        BrowserModule,
        AlertModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MdbModalModule,
        FormsModule,
        ReactiveFormsModule,
        MdbDropdownModule,
        AppRoutingModule,
        RouterModule.forRoot(routes)

    ],

    schemas: [ CUSTOM_ELEMENTS_SCHEMA ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
