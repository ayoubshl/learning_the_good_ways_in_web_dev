import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllEmployeesComponent } from './get-all-employees.component';

describe('GetAllEmployeesComponent', () => {
  let component: GetAllEmployeesComponent;
  let fixture: ComponentFixture<GetAllEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetAllEmployeesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetAllEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
