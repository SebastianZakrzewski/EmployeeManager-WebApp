import {Component, Directive, ElementRef, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import * as $ from 'jquery';
import {EmployeeService} from "./Services/EmployeeService/employee.service";
import {  trigger,
  state,
  style,
  animate,
  transition,} from "@angular/animations";
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import {EmployeeWindowComponent} from "./home/employee-window/employee-window.component";
import {ApiCallService} from "./Services/ApiCallService/api-call.service";

$('#elemId').width();

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [EmployeeService,MdbModalService,ApiCallService],
})
export class AppComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {
  }


}
