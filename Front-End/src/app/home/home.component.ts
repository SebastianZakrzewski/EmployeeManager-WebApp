import {Component, OnInit} from '@angular/core';
import {  trigger,
    state,
    style,
    animate,
    transition,} from "@angular/animations";
import {Router} from "@angular/router";
import {MdbModalService} from "mdb-angular-ui-kit/modal";
import {ModalComponent} from "./ModalWIndows/modal-addEmployee/modal.component";
import {ModalEditEmployeeComponent} from "./ModalWIndows/modal-edit-employee/modal-edit-employee.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
    animations: [trigger('colorState',
        [state('change',style({
            filter: 'brightness(30%)',
        })),
            transition('new => change', [
                animate(300)
            ]),
        ])
    ],
})

export class HomeComponent implements OnInit {
     inputStringValue = '';
     state = 'new'
    constructor(private modal:MdbModalService,private _router:Router) {}
    ngOnInit(): void {}
    openAddEmployeeModalAndChangeState() {
        this.modal.open(ModalComponent).onClose.subscribe((t) => {
            this.state = 'new';
        })
        this.state = 'change';
    }

    pipeValidation(value: string) {
        this.inputStringValue = value;
    }

    openEditEmployeeModalAndChangeState() {
        this.modal.open(ModalEditEmployeeComponent).onClose.subscribe(() =>{
            this.state = 'new'
        })
        this.state = 'change';
    }
}
