import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-modal-delete-employee',
  templateUrl: './modal-delete-component.html',
  styleUrls: ['./modal-delete-component.css']
})
export class ModalDeleteComponent {
    @Output() closeModalEmitter = new EventEmitter();
    @Input() mesage: any;
    modalRef: any;

    showErrorAlert(message: string) {

    }
}


