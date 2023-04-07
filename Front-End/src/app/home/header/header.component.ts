import {
  Component,
  EventEmitter, HostListener,
  OnInit,
  Output,
} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Router} from "@angular/router";
@HostListener('window:keyup', ['$event'])

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  public navbarOpen = false;
  @Output() createEmployeeEmitter = new EventEmitter();
  @Output() searchInputEmitter = new EventEmitter<string>();
  // @ts-ignore
  private subject$ = new BehaviorSubject<String>;
  data = this.subject$.asObservable();
  searchInput: any;
  constructor(private _router: Router) {}




  ngOnInit(): void {

  }





  @HostListener('keyup', ['$event']) onKeyDowns(event: KeyboardEvent) {
     this.onChange();
  }




  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }

  onChange() {
    this.searchInputEmitter.emit(this.searchInput);
  }

  createEmployee() {
    this.createEmployeeEmitter.emit();
  }






    onSubmit(value: FormData) {
       this.subject$.next(this.searchInput);

    }



}
