import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeWindowComponent } from './employee-window.component';

describe('EmployeeWindowComponent', () => {
  let component: EmployeeWindowComponent;
  let fixture: ComponentFixture<EmployeeWindowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeWindowComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
