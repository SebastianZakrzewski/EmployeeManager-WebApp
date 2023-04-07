import {ComponentFactory, Injectable, OnInit} from '@angular/core';
import {ApiCallService} from "../ApiCallService/api-call.service";
import {Employee} from "../../home/employee-window/Employee";
import {BehaviorSubject, Observable} from "rxjs";
import {__assign} from "tslib";
@Injectable({
  providedIn: 'root'
})
export class EmployeeService implements OnInit {
  public employeeCode!: number;
  // @ts-ignore
  private getListSubject: BehaviorSubject<Employee[]> = new BehaviorSubject<Employee[]>(null);
  // @ts-ignore
  private createSubject: BehaviorSubject<Employee> = new BehaviorSubject<Employee>(null);
  // @ts-ignore
  private updateSubject: BehaviorSubject<Employee> = new BehaviorSubject<Employee>(null);
  public listOfData = this.getListSubject.asObservable();
  public createData = this.createSubject.asObservable();
  public updateData = this.updateSubject.asObservable();

  constructor(private api:ApiCallService) {}
  public createNewEmployee(employee:Employee) {
    this.api.create(employee,"/employee/create").subscribe((value) => {
       this.createSubject.next(value);
    })
  }
  public updateEmployee(employee:Employee) {
    this.api.update<Employee>(employee,this.employeeCode,"/employee/update/").subscribe((value) => {
      this.updateSubject.next(value);
    })
  }

  public deleteEmployee(id:number)  {
     this.api.delete<Employee>(id,"/employee/delete/").subscribe(() => {
       console.log("delete success")
     });
  }
  public getListOfEmployee() {
    this.api.getListOfObjects<Employee>("/employees").subscribe((data) => {
      this.getListSubject.next(data);
    })
  }
  ngOnInit(): void {}

}
