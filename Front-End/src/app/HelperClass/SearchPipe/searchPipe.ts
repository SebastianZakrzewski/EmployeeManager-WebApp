import {Injectable, Pipe, PipeTransform} from '@angular/core';
import {Employee} from "../../home/employee-window/Employee";

@Pipe({
  name:'Search'
})

export class SearchPipe implements PipeTransform {
  constructor() {}

  transform(value:Employee[],filterString: string): Employee[] {

    if (value.length === 0 || !filterString) {
      return value;
    }

    let filteredUser: Employee[]
    filteredUser = new Array<Employee>();
    for (let emp of value) {
      if (emp.name.toLowerCase().includes(filterString.toLowerCase())) {
        filteredUser.push(emp);
      }
    }


    return filteredUser;

  }


}
