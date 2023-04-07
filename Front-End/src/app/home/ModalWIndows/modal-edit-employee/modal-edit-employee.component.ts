import {Component, EventEmitter, Output, ViewChild} from '@angular/core';
import {EmployeeService} from "../../../Services/EmployeeService/employee.service";
import {MdbModalRef} from "mdb-angular-ui-kit/modal";
import {Employee} from "../../employee-window/Employee";

@Component({
  selector: 'app-modal-edit-employee',
  templateUrl: './modal-edit-employee.component.html',
  styleUrls: ['./modal-edit-employee.component.css']
})
export class ModalEditEmployeeComponent {
    employee: Employee = {name: '', email: '', tittle: '', phone: '', file: '', code: 0};
    constructor(private refMod:MdbModalRef<ModalEditEmployeeComponent>,private employeeService: EmployeeService) {
    }
    onSubmit() {
        this.employeeService.updateEmployee(this.employee);
    }

    close() {
        this.refMod.close();
    }

}
