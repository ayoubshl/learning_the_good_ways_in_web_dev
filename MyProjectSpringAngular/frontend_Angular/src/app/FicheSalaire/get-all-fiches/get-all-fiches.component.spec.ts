import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllFichesComponent } from './get-all-fiches.component';

describe('GetAllFichesComponent', () => {
  let component: GetAllFichesComponent;
  let fixture: ComponentFixture<GetAllFichesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetAllFichesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetAllFichesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
