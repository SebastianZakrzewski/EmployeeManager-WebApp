import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EmployeeService} from "../../../Services/EmployeeService/employee.service";
import {Employee} from "../Employee";


@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  // @ts-ignore
  @Input() employee:Employee;
  @Output() editEmployeeEmitter = new EventEmitter();
  @Output() deleteEmployeeEmitter = new EventEmitter();
    file: string = 'http://localhost:4200/2748aebd-a82d-467a-952a-bb8ba03c526b';
  constructor() {}
  ngOnInit(): void {}

  deleteEmployee(code: number) {
    this.deleteEmployeeEmitter.emit(code);
  }




  editEmployee(employeeCode: number) {
    this.editEmployeeEmitter.emit(employeeCode);
  }
}
