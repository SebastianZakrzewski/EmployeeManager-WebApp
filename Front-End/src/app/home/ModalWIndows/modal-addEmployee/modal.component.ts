import {Component, EventEmitter} from '@angular/core';
import {MdbModalRef, MdbModalService} from 'mdb-angular-ui-kit/modal';
import {EmployeeService} from "../../../Services/EmployeeService/employee.service";
import {ApiCallService} from "../../../Services/ApiCallService/api-call.service";
import {Employee} from "../../employee-window/Employee";
import {filter} from "rxjs";

@Component({
  selector: 'app-modal-addEmployee',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {
  avatar: string | ArrayBuffer | null = '';
  employee: Employee = {name: '', email: '', tittle: '', phone: '', file: '', code: 0};
  constructor(public modalRef: MdbModalRef<ModalComponent>,private employeeService:EmployeeService) {}
  onSubmit() {
    this.employeeService.createNewEmployee(this.employee);
  }
     click() {
    this.modalRef.close("end");
   }
     onFileChange(event:any) {
       const files = event.target.files as FileList;
       if (files.length > 0) {
         this.avatar = URL.createObjectURL(files[0]);}
       this.convertFileToBase64(files[0])
   }
     convertFileToBase64(file:Blob) {
       let reader = new FileReader();
       reader.readAsDataURL(file);
       reader.onload = () => {
       this.employee.file  = reader.result as string;

    }


   }



}
