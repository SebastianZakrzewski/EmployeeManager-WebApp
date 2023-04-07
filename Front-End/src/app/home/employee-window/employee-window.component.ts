import {
    Component, EventEmitter, Input,
    OnInit, Output,
} from '@angular/core';
import {Employee} from "./Employee";
import {EmployeeService} from "../../Services/EmployeeService/employee.service";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {ModalEditEmployeeComponent} from "../ModalWIndows/modal-edit-employee/modal-edit-employee.component";

@Component({
  selector: 'app-employee-window',
  templateUrl: './employee-window.component.html',
  styleUrls: ['./employee-window.component.css'],
})

export class EmployeeWindowComponent implements OnInit {
    @Output()  editEmitter = new EventEmitter();
    employee: Employee[] = [];
    @Input() searchInput = '';
  constructor(private employeeService: EmployeeService, private httpClient: HttpClient) {}
     ngOnInit(): void {
     this.addDataToArray();
  }

  addDataToArray() {
      this.employeeService.createData.subscribe((data) => {
          this.employee.push(data);});

      this.employeeService.getListOfEmployee();
      this.employeeService.listOfData.subscribe((data) => {
          this.employee = data;
        })
    }

    deleteEmployee(code: number) {
      this.employeeService.deleteEmployee(code);
      let number =  this.employee.findIndex((x) => x.code == code);
      this.employee.splice(number,1);
    }

    editEmployee(id: number) {
      this.editEmitter.emit();
      this.employeeService.employeeCode = id;
      this.employeeService.updateData.subscribe((value) => {
          let index = this.employee.findIndex((x) => x.code == value.code);
          this.employee[index] = value;})


}

}

